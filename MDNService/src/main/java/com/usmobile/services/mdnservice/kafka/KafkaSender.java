package com.usmobile.services.mdnservice.kafka;

import com.usmobile.shared.model.constants.KafkaConstants;
import com.usmobile.shared.model.data.MDNCycleEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(MDNCycleEventDTO mdnCycleEventDTO){ kafkaTemplate.send(KafkaConstants.TopicNames.MDN_CYCLE_TOPIC, mdnCycleEventDTO);}
}
