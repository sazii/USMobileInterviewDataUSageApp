package com.usmobile.services.dataUsageService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class DataUsageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataUsageServiceApplication.class, args);
    }


}
