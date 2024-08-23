package com.usmobile.shared.model.data;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseData {
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
}
