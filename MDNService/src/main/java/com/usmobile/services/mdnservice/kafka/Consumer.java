package com.usmobile.services.mdnservice.kafka;

import com.usmobile.services.mdnservice.service.UserInfoService;
import com.usmobile.shared.model.constants.KafkaConstants;
import com.usmobile.shared.model.data.UserEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Consumer {
    private final UserInfoService userInfoService;

    @KafkaListener(topics = {KafkaConstants.TopicNames.USER_TOPIC})
    public void consume(UserEventDto eventDto) {
        System.out.println(eventDto.toString());
        userInfoService.save(eventDto);
    }
}
