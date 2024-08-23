package com.usmobile.services.mdnservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class MDNCanNotBeEmptyException extends AbstractApiException {
    public MDNCanNotBeEmptyException(String modifyingType, String userId) {
        super(ExceptionError.builder().code(2001).message("MDN can not be empty while  " + modifyingType + " for user " + userId + "; please fill the area").build());
    }
}
