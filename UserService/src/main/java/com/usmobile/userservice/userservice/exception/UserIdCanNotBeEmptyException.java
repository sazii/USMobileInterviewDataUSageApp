package com.usmobile.userservice.userservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class UserIdCanNotBeEmptyException extends AbstractApiException {
    public UserIdCanNotBeEmptyException(String modifyingType) {
        super(ExceptionError.builder().code(1004).message("userId can not be empty for " + modifyingType + " user").build());
    }
}
