package com.usmobile.services.dataUsageService.data;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class CycleInfoDTO {
    String cycleId;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
