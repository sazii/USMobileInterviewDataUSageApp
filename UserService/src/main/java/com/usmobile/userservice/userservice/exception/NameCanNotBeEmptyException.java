package com.usmobile.userservice.userservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class NameCanNotBeEmptyException extends AbstractApiException {
    public NameCanNotBeEmptyException(String modifyingType) {
        super(ExceptionError.builder().code(1001).message("Name can not be empty for " + modifyingType + " user").build());
    }
}
