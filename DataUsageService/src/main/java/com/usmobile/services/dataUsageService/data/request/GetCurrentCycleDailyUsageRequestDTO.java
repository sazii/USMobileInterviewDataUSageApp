package com.usmobile.services.dataUsageService.data.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCurrentCycleDailyUsageRequestDTO {
    String userId;
    String mdn;
    int page;
    int pageSize;
}
