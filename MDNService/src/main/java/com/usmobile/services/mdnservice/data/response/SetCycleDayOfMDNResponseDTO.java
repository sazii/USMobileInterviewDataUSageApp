package com.usmobile.services.mdnservice.data.response;

import com.usmobile.services.mdnservice.data.MDNUserInfoDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SetCycleDayOfMDNResponseDTO {
    MDNUserInfoDTO mdnUserInfoDTO;
    String message;
}
