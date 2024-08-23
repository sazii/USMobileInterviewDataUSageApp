package com.usmobile.shared.model.validation;

import org.springframework.util.ObjectUtils;

public class AndValidator<T> extends AbstractCompositeValidator<T>{
    @Override
    public boolean isSatisfiedBy(T validatable) {
        return ObjectUtils.isEmpty(validatorList) || validatorList.stream().allMatch(validator -> {
            try {
                return validator.isSatisfiedBy(validatable);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
