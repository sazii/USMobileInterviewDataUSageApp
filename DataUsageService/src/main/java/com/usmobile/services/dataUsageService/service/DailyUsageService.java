package com.usmobile.services.dataUsageService.service;

import com.usmobile.services.dataUsageService.data.DailyUsageDTO;
import com.usmobile.services.dataUsageService.data.UserIdMDNPair;
import com.usmobile.services.dataUsageService.data.request.GetCurrentCycleDailyUsageRequestDTO;
import com.usmobile.services.dataUsageService.domain.CurrentCycle;
import com.usmobile.services.dataUsageService.domain.Cycle;
import com.usmobile.services.dataUsageService.domain.DailyUsage;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface DailyUsageService {

    void save(List<DailyUsage> dailyUsageList);

    Page<DailyUsage> getCycleDailyUsageOfMDN(CurrentCycle cycle, int page, int pageSize);
}
