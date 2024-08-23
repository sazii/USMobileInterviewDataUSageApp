package com.usmobile.userservice.userservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class LastNameCanNotBeEmptyException extends AbstractApiException {
    public LastNameCanNotBeEmptyException(String modifyingType) {
        super(ExceptionError.builder().message("LastName Can not be empty for " + modifyingType + " user").code(1002).build());
    }
}
