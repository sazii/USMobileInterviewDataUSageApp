package com.usmobile.userservice.userservice.validation;

import com.usmobile.shared.model.validation.AndValidator;
import com.usmobile.userservice.userservice.constants.UserServiceConstants;
import com.usmobile.userservice.userservice.data.UserRequestDto;
import com.usmobile.userservice.userservice.data.request.CreateUserRequestDto;
import com.usmobile.userservice.userservice.data.request.UpdateUserRequestDTO;
import com.usmobile.userservice.userservice.repository.UserRepository;
import com.usmobile.userservice.userservice.validation.validationRules.*;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;
    public boolean runValidationsForCreate(CreateUserRequestDto requestDTO){
        AndValidator<UserRequestDto> validatorList  = new AndValidator<>();
        validatorList.addValidator(new NameCanNotBeEmptyRule(UserServiceConstants.ModifyingType.CREATE));
        validatorList.addValidator(new LastNameCanNotBeEmptyRule(UserServiceConstants.ModifyingType.CREATE));
        validatorList.addValidator(new PasswordCanNotBeEmptyRule(UserServiceConstants.ModifyingType.CREATE));
        validatorList.addValidator(new EmailCanNotBeEmptyRule(UserServiceConstants.ModifyingType.CREATE));
        validatorList.addValidator(new EmailAlreadyUsedByAnotherUserRule(UserServiceConstants.ModifyingType.CREATE, userRepository));
        return validatorList.isSatisfiedBy(requestDTO.getUserRequestDto());
    }

    public boolean runValidationsForUpdate(UpdateUserRequestDTO requestDTO){
        AndValidator<UpdateUserRequestDTO> validatorList = new AndValidator<>();
        validatorList.addValidator(new UserIdCanNotBeEmptyRule(UserServiceConstants.ModifyingType.UPDATE));
        AndValidator<UserRequestDto> validatorList2 = new AndValidator<>();
        if(!StringUtils.isEmpty(requestDTO.getUserRequestDto().getEmail())){
            validatorList2.addValidator(new EmailAlreadyUsedByAnotherUserRule(UserServiceConstants.ModifyingType.UPDATE, userRepository));
        }
        return validatorList.isSatisfiedBy(requestDTO) && validatorList2.isSatisfiedBy(requestDTO.getUserRequestDto());
    }
}
