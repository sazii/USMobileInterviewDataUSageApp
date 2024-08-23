package com.usmobile.services.dataUsageService.service.impl;

import com.usmobile.services.dataUsageService.data.DailyUsageDTO;
import com.usmobile.services.dataUsageService.data.request.GetCurrentCycleDailyUsageRequestDTO;
import com.usmobile.services.dataUsageService.data.response.GetCurrentCycleDailyUsageResponseDTO;
import com.usmobile.services.dataUsageService.domain.CurrentCycle;
import com.usmobile.services.dataUsageService.domain.Cycle;
import com.usmobile.services.dataUsageService.domain.DailyUsage;
import com.usmobile.services.dataUsageService.exception.CurrentCycleCouldNotBeFoundException;
import com.usmobile.services.dataUsageService.repository.CurrentCycleRepository;
import com.usmobile.services.dataUsageService.service.CurrentCycleService;
import com.usmobile.services.dataUsageService.service.DailyUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CurrentCycleServiceImpl implements CurrentCycleService {

    private final CurrentCycleRepository repository;

    private final DailyUsageService dailyUsageService;
    @Override
    public void updateCurrentCycle(Cycle cycle) {
        CurrentCycle currentCycle = repository.findCurrentCycleByUserIdAndMdn(cycle.getUserId(), cycle.getMdn());
        if(ObjectUtils.isEmpty(currentCycle)){
            currentCycle = CurrentCycle.builder()
                    .userId(cycle.getUserId())
                    .mdn(cycle.getMdn())
                    .build();
            currentCycle.setCreatedDate(LocalDateTime.now());
        }
        currentCycle.setCurrentCycleId(cycle.getCycleId());
        currentCycle.setStartDate(cycle.getStartDate());
        currentCycle.setEndDate(cycle.getEndDate());
        currentCycle.setUpdatedDate(LocalDateTime.now());
        repository.save(currentCycle);
    }

    @Override
    public GetCurrentCycleDailyUsageResponseDTO getDailyUsageOfMDN(GetCurrentCycleDailyUsageRequestDTO requestDTO) throws Exception{
        //validate request
        CurrentCycle currentCycle = findCurrentCycleByUserIdAndMDN(requestDTO.getUserId(), requestDTO.getMdn());
        Page<DailyUsage> dailyUsages = dailyUsageService.getCycleDailyUsageOfMDN(currentCycle, requestDTO.getPage(), requestDTO.getPageSize());
        return GetCurrentCycleDailyUsageResponseDTO.builder()
                .dailyUsageDTOs(dailyUsages.map(dailyUsage -> DailyUsageDTO.builder()
                        .dataUsedInMB(dailyUsage.getUsedInMb())
                        .date(dailyUsage.getUsageDate())
                        .build()))
                .userId(requestDTO.getUserId())
                .mdn(requestDTO.getMdn())
                .message("dailyUsages are successfully retrieved")
                .build();
    }

    @Override
    public CurrentCycle findCurrentCycleByUserIdAndMDN(String userId, String mdn) throws Exception{
        CurrentCycle currentCycle = repository.findCurrentCycleByUserIdAndMdn(userId, mdn);
        if(ObjectUtils.isEmpty(currentCycle)){
            throw new CurrentCycleCouldNotBeFoundException(userId, mdn);
        }
        return currentCycle;
    }
}
