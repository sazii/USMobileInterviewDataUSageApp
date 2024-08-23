package com.usmobile.services.dataUsageService.service.impl;

import com.usmobile.services.dataUsageService.constant.DataUsageConstants;
import com.usmobile.services.dataUsageService.data.DailyUsageDTO;
import com.usmobile.services.dataUsageService.data.UserIdMDNPair;
import com.usmobile.services.dataUsageService.data.request.GetCurrentCycleDailyUsageRequestDTO;
import com.usmobile.services.dataUsageService.domain.CurrentCycle;
import com.usmobile.services.dataUsageService.domain.Cycle;
import com.usmobile.services.dataUsageService.domain.DailyUsage;
import com.usmobile.services.dataUsageService.repository.CurrentCycleRepository;
import com.usmobile.services.dataUsageService.repository.CycleRepository;
import com.usmobile.services.dataUsageService.repository.DailyUsageRepository;
import com.usmobile.services.dataUsageService.service.DailyUsageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailyUsageServiceImpl implements DailyUsageService {

    private final DailyUsageRepository repository;
    @Override
    public void save(List<DailyUsage> dailyUsageList) {
        log.info("usage is saving");
        repository.saveAll(dailyUsageList);
    }



    public Page<DailyUsage> getCycleDailyUsageOfMDN( CurrentCycle cycle, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return repository.findDailyUsagesByUserIdAndMdnAndUsageDateBetween(cycle.getUserId(),
                cycle.getMdn(),
                cycle.getStartDate(),
                cycle.getEndDate(),
                pageable);
    }
}
