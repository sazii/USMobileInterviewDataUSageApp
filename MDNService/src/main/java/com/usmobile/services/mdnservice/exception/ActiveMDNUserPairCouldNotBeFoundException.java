package com.usmobile.services.mdnservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;

public class ActiveMDNUserPairCouldNotBeFoundException extends AbstractApiException {
    public ActiveMDNUserPairCouldNotBeFoundException(String userId, String mdn) {
        super(ExceptionError.builder().code(2006).message("user" + userId + " has not mdn " + mdn + " in system").build());
    }
}
