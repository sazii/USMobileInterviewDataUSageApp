package com.usmobile.services.dataUsageService.service;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

public interface CSVProcessService {
    KStream<String, String> processCsv(StreamsBuilder streamsBuilder);
}
