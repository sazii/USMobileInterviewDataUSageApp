package com.usmobile.userservice.userservice.validation.validationRules;

import com.usmobile.shared.model.validation.AbstractCompositeValidator;
import com.usmobile.userservice.userservice.data.UserDto;
import com.usmobile.userservice.userservice.data.UserRequestDto;
import com.usmobile.userservice.userservice.exception.EmailAlreadyUsedException;
import com.usmobile.userservice.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EmailAlreadyUsedByAnotherUserRule extends AbstractCompositeValidator<UserRequestDto> {

    private final String modifyingType;
    private final UserRepository repository;
    @Override
    public boolean isSatisfiedBy(UserRequestDto validatable) throws Exception {
        boolean rule = !repository.existsUserByEmailAndActiveTrue(validatable.getEmail());
        if(!rule){
            throw new EmailAlreadyUsedException(validatable.getEmail(), modifyingType);
        }
        return true;
    }
}
