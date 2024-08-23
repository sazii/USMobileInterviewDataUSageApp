package com.usmobile.services.dataUsageService.controller;

import com.usmobile.services.dataUsageService.data.request.GetCurrentCycleDailyUsageRequestDTO;
import com.usmobile.services.dataUsageService.data.request.GetCycleHistoryRequestDTO;
import com.usmobile.services.dataUsageService.data.response.GetCurrentCycleDailyUsageResponseDTO;
import com.usmobile.services.dataUsageService.data.response.GetCycleHistoryResponseDTO;
import com.usmobile.services.dataUsageService.service.CycleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cycle")
public class CycleController {

    private final CycleService service;

    public CycleController(CycleService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public GetCycleHistoryResponseDTO getCycleHistoryOfMDN(@RequestBody GetCycleHistoryRequestDTO requestDTO){
        return service.getCycleHistoryOfMDN(requestDTO);
    }



}
