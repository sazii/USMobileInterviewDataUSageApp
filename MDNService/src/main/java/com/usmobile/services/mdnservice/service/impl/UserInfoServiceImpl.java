package com.usmobile.services.mdnservice.service.impl;

import com.usmobile.services.mdnservice.domain.UserInfo;
import com.usmobile.services.mdnservice.repository.UserInfoRepository;
import com.usmobile.services.mdnservice.service.UserInfoService;
import com.usmobile.shared.model.data.UserEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository repository;
    @Override
    public void save(UserEventDto eventDto) {
        UserInfo userInfo = UserInfo.builder()
                .userId(eventDto.getUserId())
                .active(eventDto.isActive())
                .mdnMap(new HashMap<>())
                .build();
        userInfo.setCreatedDate(LocalDateTime.now());
        repository.save(userInfo);
    }
}
