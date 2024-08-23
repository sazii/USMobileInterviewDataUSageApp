package com.usmobile.shared.model.exception;

public class AbstractApiException extends Exception{
    int code;
    public AbstractApiException(ExceptionError err){
        super(err.getMessage());
        this.code = err.getCode();
    }

    public int getCode(){
        return code;
    }
}
