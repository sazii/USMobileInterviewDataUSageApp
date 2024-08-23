package com.usmobile.services.mdnservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class UserCouldNotBeFoundException extends AbstractApiException {
    public UserCouldNotBeFoundException(String userId) {
        super(ExceptionError.builder().code(2002).message("User could not be found with id " + userId).build());
    }
}
