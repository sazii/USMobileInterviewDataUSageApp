package com.usmobile.services.dataUsageService.service;

import com.usmobile.services.dataUsageService.data.request.GetCurrentCycleDailyUsageRequestDTO;
import com.usmobile.services.dataUsageService.data.response.GetCurrentCycleDailyUsageResponseDTO;
import com.usmobile.services.dataUsageService.domain.CurrentCycle;
import com.usmobile.services.dataUsageService.domain.Cycle;

public interface CurrentCycleService {

    void updateCurrentCycle(Cycle cycle);

    CurrentCycle findCurrentCycleByUserIdAndMDN(String userId, String mdn) throws Exception;

    GetCurrentCycleDailyUsageResponseDTO getDailyUsageOfMDN(GetCurrentCycleDailyUsageRequestDTO requestDTO) throws Exception;
}
