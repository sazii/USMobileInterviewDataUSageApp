package com.usmobile.services.mdnservice.service;


import com.usmobile.services.mdnservice.data.request.AddMDNRequestDTO;
import com.usmobile.services.mdnservice.data.request.SetCycleDayOfMDNRequestDTO;
import com.usmobile.services.mdnservice.data.response.AddMDNResponseDTO;
import com.usmobile.services.mdnservice.data.response.SetCycleDayOfMDNResponseDTO;


public interface MDNService {
    AddMDNResponseDTO addMDN(AddMDNRequestDTO requestDTO) throws Exception;

    SetCycleDayOfMDNResponseDTO setCycleDayOfMDN(SetCycleDayOfMDNRequestDTO requestDTO) throws Exception;
}
