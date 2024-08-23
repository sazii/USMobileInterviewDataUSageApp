package com.usmobile.services.mdnservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class UserHasAlreadyThisMDNException extends AbstractApiException {
    public UserHasAlreadyThisMDNException(String userId , String mdn) {
        super(ExceptionError.builder().code(2003).message("user " + userId + " has already this mdn " + mdn).build());
    }
}
