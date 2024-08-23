package com.usmobile.services.dataUsageService.controller;

import com.usmobile.services.dataUsageService.service.MockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {
    private final MockService service;

    public MockController(MockService service) {
        this.service = service;
    }

    @PostMapping("/createPreviousCycleData")
    public boolean createPreviousCycleData() {
        return service.createPreviousCycleData();
    }


}
