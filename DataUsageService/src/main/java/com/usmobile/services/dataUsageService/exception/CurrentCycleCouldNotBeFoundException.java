package com.usmobile.services.dataUsageService.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class CurrentCycleCouldNotBeFoundException extends AbstractApiException {
    public CurrentCycleCouldNotBeFoundException(String userId, String mdn) {
        super(ExceptionError.builder().message("There is no current cycle in system for user " + userId + " with " + mdn).build());
    }
}
