package com.usmobile.userservice.userservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class EmailAlreadyUsedException extends AbstractApiException {
    public EmailAlreadyUsedException(String email, String modifyingType) {
        super(ExceptionError
                .builder()
                .code(1007)
                .message(email + " is already registered in the system; please use another email for " + modifyingType)
                .build());
    }
}
