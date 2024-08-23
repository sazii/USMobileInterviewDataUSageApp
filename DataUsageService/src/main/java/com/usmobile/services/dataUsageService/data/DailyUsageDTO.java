package com.usmobile.services.dataUsageService.data;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DailyUsageDTO {
    LocalDateTime date;
    Long dataUsedInMB;
}
