package com.usmobile.userservice.userservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class PasswordCanNotBeEmptyException extends AbstractApiException {
    public PasswordCanNotBeEmptyException(String modifyingType) {
        super(ExceptionError.builder().message("Password Cannot Be Empty for " + modifyingType + " user").code(1000).build());
    }
}
