package com.usmobile.services.dataUsageService.data.response;

import com.usmobile.services.dataUsageService.data.DailyUsageDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class GetCurrentCycleDailyUsageResponseDTO {
    Page<DailyUsageDTO> dailyUsageDTOs;
    String userId;
    String mdn;
    String message;
}
