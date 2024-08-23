package com.usmobile.userservice.userservice.validation.validationRules;

import com.usmobile.shared.model.validation.AbstractCompositeValidator;
import com.usmobile.userservice.userservice.data.UserDto;
import com.usmobile.userservice.userservice.data.UserRequestDto;
import com.usmobile.userservice.userservice.exception.EmailCanNotBeEmptyException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailCanNotBeEmptyRule extends AbstractCompositeValidator<UserRequestDto> {

    private final String modifyingType;
    @Override
    public boolean isSatisfiedBy(UserRequestDto validatable) throws Exception {
        boolean rule = StringUtils.isNotEmpty(validatable.getEmail());
        if(!rule){
            throw new EmailCanNotBeEmptyException(validatable.getFullName(), modifyingType);
        }
        return true;
    }
}
