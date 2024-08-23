package com.usmobile.services.mdnservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class MdnAlreadyExistsException extends AbstractApiException {
    public MdnAlreadyExistsException(String mdn) {
        super(ExceptionError.builder().code(2000).message("Mdn " + mdn + " is already registered in the system" ).build());
    }
}
