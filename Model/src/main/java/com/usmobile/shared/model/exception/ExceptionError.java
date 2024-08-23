package com.usmobile.shared.model.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionError {
    String message;
    int code;
}
