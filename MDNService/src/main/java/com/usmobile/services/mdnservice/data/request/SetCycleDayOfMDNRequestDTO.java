package com.usmobile.services.mdnservice.data.request;

import com.usmobile.services.mdnservice.data.MDNUserInfoDTO;
import lombok.Data;

@Data
public class SetCycleDayOfMDNRequestDTO {
    MDNUserInfoDTO mdnUserInfoDTO;
    int dayOfMonth;

}
