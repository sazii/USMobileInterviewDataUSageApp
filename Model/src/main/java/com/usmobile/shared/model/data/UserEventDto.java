package com.usmobile.shared.model.data;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEventDto {
    String userId;
    boolean active;
}
