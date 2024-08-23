package com.usmobile.services.dataUsageService.service.impl;

import com.usmobile.services.dataUsageService.constant.DataUsageConstants;
import com.usmobile.services.dataUsageService.domain.DailyUsage;
import com.usmobile.services.dataUsageService.service.CSVProcessService;
import com.usmobile.services.dataUsageService.service.DailyUsageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.Cancellable;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@Configuration
public class CSVProcessServiceImpl implements CSVProcessService {

    private final DailyUsageService dailyUsageService;
    @Bean
    public KStream<String, String> processCsv(StreamsBuilder streamsBuilder) {

        KStream<String, String> dailyUsageStream = streamsBuilder
                .stream(DataUsageConstants.KafkaStream.TOPIC_NAME, Consumed.with(Serdes.String(), Serdes.String()));
        dailyUsageStream.transform(() -> new Transformer<String, String, KeyValue<String, List<DailyUsage>>>(){
            private final List<DailyUsage> dailyUsageBatch = new ArrayList<>();

            private ProcessorContext context;
            private Cancellable schedule;
            @Override
            public void init(ProcessorContext processorContext) {
                this.context = processorContext;
                schedule = context.schedule(
                        Duration.ofMillis(DataUsageConstants.KafkaStream.WAIT_DURATION_FOR_FLUSH),
                        PunctuationType.WALL_CLOCK_TIME,
                        this::punctuate);
            }

            private void punctuate(long l) {
                flushBatch(DataUsageConstants.KafkaStream.REST_BATCH_KEY);
            }

            private void flushBatch(String key) {
                if(!dailyUsageBatch.isEmpty()){
                    context.forward(key, new ArrayList<>(dailyUsageBatch));
                    dailyUsageBatch.clear();
                }
            }

            @Override
            public KeyValue<String, List<DailyUsage>> transform(String key, String value) {
                String[] values = value.split(",");
                if(isNotHeader(values[0])){
                    DailyUsage dailyUsage = transformToDailyUsage(values);
                    dailyUsage.setCreatedDate(LocalDateTime.now());
                    dailyUsageBatch.add(dailyUsage);
                    if(dailyUsageBatch.size() == DataUsageConstants.KafkaStream.BATCH_SIZE){
                        List<DailyUsage> batchToSend = new ArrayList<>(dailyUsageBatch);
                        dailyUsageBatch.clear();
                        return new KeyValue<>(key, batchToSend);
                    }
                }
                return null;
            }

            private DailyUsage transformToDailyUsage(String[] values){
                String mdn = values[DataUsageConstants.CSVFields.fieldIndexMap.get(DataUsageConstants.CSVFields.MDN)];
                String userId = values[DataUsageConstants.CSVFields.fieldIndexMap.get(DataUsageConstants.CSVFields.USER_ID)];
                LocalDateTime usageDate = LocalDateTime.of (
                        Integer.parseInt(values[DataUsageConstants.CSVFields.fieldIndexMap.get(DataUsageConstants.CSVFields.USAGE_YEAR)]),
                        Integer.parseInt(values[DataUsageConstants.CSVFields.fieldIndexMap.get(DataUsageConstants.CSVFields.USAGE_MONTH)]),
                        Integer.parseInt(values[DataUsageConstants.CSVFields.fieldIndexMap.get(DataUsageConstants.CSVFields.USAGE_DAY)]),
                        Integer.parseInt(values[DataUsageConstants.CSVFields.fieldIndexMap.get(DataUsageConstants.CSVFields.USED_HOUR)]),
                        Integer.parseInt(values[DataUsageConstants.CSVFields.fieldIndexMap.get(DataUsageConstants.CSVFields.USED_MINUTE)])
                        );
                Long usedInMb = Long.valueOf(values[DataUsageConstants.CSVFields.fieldIndexMap.get(DataUsageConstants.CSVFields.USED_DATA_IN_MB)]);
                return DailyUsage.builder()
                        .mdn(mdn)
                        .userId(userId)
                        .usageDate(usageDate)
                        .usedInMb(usedInMb)
                        .build();
            }

            private boolean isNotHeader(String firstColValue) {
                return !firstColValue.equals(DataUsageConstants.CSVFields.MDN);
            }

            @Override
            public void close() {
                flushBatch(DataUsageConstants.KafkaStream.FINAL_BATCH_KEY);
                if(schedule != null){
                    schedule.cancel();
                }
            }
        }).foreach( (key, dailyUsageBatch) -> {
             log.info("processed a batch of size {}", dailyUsageBatch.size());
             dailyUsageService.save(dailyUsageBatch);
        });
        return dailyUsageStream;
    }


}
