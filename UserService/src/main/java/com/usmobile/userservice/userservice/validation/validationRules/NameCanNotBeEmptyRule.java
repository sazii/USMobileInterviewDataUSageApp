package com.usmobile.userservice.userservice.validation.validationRules;

import com.usmobile.shared.model.validation.AbstractCompositeValidator;
import com.usmobile.userservice.userservice.data.UserDto;
import com.usmobile.userservice.userservice.data.UserRequestDto;
import com.usmobile.userservice.userservice.exception.NameCanNotBeEmptyException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NameCanNotBeEmptyRule extends AbstractCompositeValidator<UserRequestDto> {

    private final String modifyingType;
    @Override
    public boolean isSatisfiedBy(UserRequestDto validatable) throws Exception {
        boolean rule = StringUtils.isNotEmpty(validatable.getFirstName());
        if(!rule){
            throw new NameCanNotBeEmptyException(modifyingType);
        }
        return true;
    }
}
