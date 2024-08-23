package com.usmobile.shared.model.validation;

public interface Validator<T> {
    boolean isSatisfiedBy(T validatable) throws Exception;
}
