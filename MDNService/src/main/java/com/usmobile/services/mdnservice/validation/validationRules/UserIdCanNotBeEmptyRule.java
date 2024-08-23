package com.usmobile.services.mdnservice.validation.validationRules;

import com.usmobile.services.mdnservice.data.MDNUserInfoDTO;
import com.usmobile.services.mdnservice.data.request.AddMDNRequestDTO;
import com.usmobile.services.mdnservice.exception.UserIdCanNotBeEmptyException;
import com.usmobile.shared.model.validation.AbstractCompositeValidator;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserIdCanNotBeEmptyRule extends AbstractCompositeValidator<MDNUserInfoDTO> {

    private final String modifyingType;
    @Override
    public boolean isSatisfiedBy(MDNUserInfoDTO validatable) throws Exception {
        boolean rule = StringUtils.isNotEmpty(validatable.getUserId());
        if(!rule){
            throw  new UserIdCanNotBeEmptyException(modifyingType);
        }
        return false;
    }
}
