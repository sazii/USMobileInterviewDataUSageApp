package com.usmobile.shared.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MDNCycleEventDTO {
    String mdn;
    String userId;
    int cycleDay;
}
