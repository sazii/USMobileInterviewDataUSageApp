package com.usmobile.services.dataUsageService.kafka;

import com.usmobile.services.dataUsageService.service.CycleService;
import com.usmobile.shared.model.constants.KafkaConstants;
import com.usmobile.shared.model.data.MDNCycleEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Consumer {

    private final CycleService cycleService;
    @KafkaListener(topics = {KafkaConstants.TopicNames.MDN_CYCLE_TOPIC})
    public void consume(MDNCycleEventDTO mdnCycleEventDTO){
        log.info(mdnCycleEventDTO + " is consumed");
        cycleService.createFirstCycleWithNewCycleDayOfMDN(mdnCycleEventDTO);
    }
}
