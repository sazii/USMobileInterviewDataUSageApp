package com.usmobile.services.mdnservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class UserIdCanNotBeEmptyException extends AbstractApiException {
    public UserIdCanNotBeEmptyException(String modifyingType) {
        super(ExceptionError.builder().code(2004).message("userId can not be empty for " + modifyingType + " mdn").build());
    }
}
