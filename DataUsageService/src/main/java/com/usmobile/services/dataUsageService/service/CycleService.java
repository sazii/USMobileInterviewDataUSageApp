package com.usmobile.services.dataUsageService.service;

import com.usmobile.services.dataUsageService.data.request.GetCycleHistoryRequestDTO;
import com.usmobile.services.dataUsageService.data.response.GetCycleHistoryResponseDTO;
import com.usmobile.shared.model.data.MDNCycleEventDTO;

public interface CycleService {
    void createFirstCycleWithNewCycleDayOfMDN(MDNCycleEventDTO mdnCycleEventDTO);

    GetCycleHistoryResponseDTO getCycleHistoryOfMDN(GetCycleHistoryRequestDTO requestDTO);
}
