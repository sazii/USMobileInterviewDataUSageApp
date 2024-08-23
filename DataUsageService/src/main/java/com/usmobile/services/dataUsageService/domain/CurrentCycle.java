package com.usmobile.services.dataUsageService.domain;


import com.usmobile.shared.model.data.BaseData;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;

import java.time.LocalDateTime;

@Data
@Builder
@CompoundIndex(name = "userId_mdn_idx", def = "{'userId' : 1, 'mdn' : 1}", unique = true)
public class CurrentCycle extends BaseData {
    @Id
    String id;
    String mdn;
    String userId;
    String currentCycleId;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
