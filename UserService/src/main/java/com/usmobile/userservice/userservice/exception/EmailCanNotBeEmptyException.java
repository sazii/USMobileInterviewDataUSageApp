package com.usmobile.userservice.userservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class EmailCanNotBeEmptyException extends AbstractApiException {
    public EmailCanNotBeEmptyException(String name, String modifyingType) {
        super(ExceptionError.builder().code(1003).message("Email can not be empty for user : " + name + " during " + modifyingType).build());
    }
}
