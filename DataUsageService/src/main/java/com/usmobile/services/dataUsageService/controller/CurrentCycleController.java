package com.usmobile.services.dataUsageService.controller;

import com.usmobile.services.dataUsageService.data.request.GetCurrentCycleDailyUsageRequestDTO;
import com.usmobile.services.dataUsageService.data.response.GetCurrentCycleDailyUsageResponseDTO;
import com.usmobile.services.dataUsageService.service.CurrentCycleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currentCycle")
public class CurrentCycleController {
    private final CurrentCycleService service;

    public CurrentCycleController(CurrentCycleService service) {
        this.service = service;
    }

    @GetMapping("/dailyUsage")
    public GetCurrentCycleDailyUsageResponseDTO getDailyUsage(@RequestBody GetCurrentCycleDailyUsageRequestDTO requestDTO) throws Exception{
        return service.getDailyUsageOfMDN(requestDTO);
    }
}
