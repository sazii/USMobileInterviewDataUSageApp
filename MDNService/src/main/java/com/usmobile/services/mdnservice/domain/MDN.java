package com.usmobile.services.mdnservice.domain;

import com.usmobile.shared.model.data.BaseData;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class MDN extends BaseData {
    String mdn;
    LocalDateTime startDate;
    LocalDateTime endDate;
    boolean active;
}
