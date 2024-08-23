package com.usmobile.services.mdnservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class CycleDayisNotValidException extends AbstractApiException {
    public CycleDayisNotValidException() {
        super(ExceptionError.builder().code(2008).message("Please enter a value btw 1 and 31").build());
    }
}
