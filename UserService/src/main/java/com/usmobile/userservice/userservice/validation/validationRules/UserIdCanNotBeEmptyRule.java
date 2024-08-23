package com.usmobile.userservice.userservice.validation.validationRules;

import com.usmobile.shared.model.validation.AbstractCompositeValidator;
import com.usmobile.userservice.userservice.data.UserDto;
import com.usmobile.userservice.userservice.data.request.UpdateUserRequestDTO;
import com.usmobile.userservice.userservice.exception.UserIdCanNotBeEmptyException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserIdCanNotBeEmptyRule extends AbstractCompositeValidator<UpdateUserRequestDTO> {
    private final String modifyingType;
    @Override
    public boolean isSatisfiedBy(UpdateUserRequestDTO validatable) throws Exception {
        boolean rule = StringUtils.isNotEmpty(validatable.getUserId());
        if(!rule){
            throw new UserIdCanNotBeEmptyException(modifyingType);
        }
        return true;
    }
}
