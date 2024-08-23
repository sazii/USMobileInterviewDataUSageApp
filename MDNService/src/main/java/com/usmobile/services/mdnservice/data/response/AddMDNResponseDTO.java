package com.usmobile.services.mdnservice.data.response;

import com.usmobile.services.mdnservice.data.MDNUserInfoDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddMDNResponseDTO {
    MDNUserInfoDTO mdnUserInfoDTO;
    String message;
}
