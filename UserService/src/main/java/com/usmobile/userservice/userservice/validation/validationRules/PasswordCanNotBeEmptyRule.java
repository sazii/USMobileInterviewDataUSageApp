package com.usmobile.userservice.userservice.validation.validationRules;

import com.usmobile.shared.model.validation.AbstractCompositeValidator;
import com.usmobile.userservice.userservice.data.UserDto;
import com.usmobile.userservice.userservice.data.UserRequestDto;
import com.usmobile.userservice.userservice.exception.PasswordCanNotBeEmptyException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordCanNotBeEmptyRule extends AbstractCompositeValidator<UserRequestDto> {

    private final String modifyingType;
    @Override
    public boolean isSatisfiedBy(UserRequestDto validatable) throws PasswordCanNotBeEmptyException {
        boolean rule = StringUtils.isNotEmpty(validatable.getPassword());
        if(!rule){
            throw new PasswordCanNotBeEmptyException(modifyingType);
        }
        return true;
    }
}
