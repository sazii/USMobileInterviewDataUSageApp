package com.usmobile.services.mdnservice.controller;


import com.usmobile.services.mdnservice.data.request.AddMDNRequestDTO;
import com.usmobile.services.mdnservice.data.request.SetCycleDayOfMDNRequestDTO;
import com.usmobile.services.mdnservice.data.response.AddMDNResponseDTO;
import com.usmobile.services.mdnservice.data.response.SetCycleDayOfMDNResponseDTO;
import com.usmobile.services.mdnservice.service.MDNService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mdn")
public class MDNController {

    private final MDNService service;

    public MDNController(MDNService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public AddMDNResponseDTO addMDN(@RequestBody AddMDNRequestDTO requestDTO) throws Exception {
        return service.addMDN(requestDTO);
    }

    @PostMapping("/setCycleDay")
    public SetCycleDayOfMDNResponseDTO setCycleDayOfMDN(@RequestBody SetCycleDayOfMDNRequestDTO requestDTO) throws Exception {
        return service.setCycleDayOfMDN(requestDTO);
    }
}
