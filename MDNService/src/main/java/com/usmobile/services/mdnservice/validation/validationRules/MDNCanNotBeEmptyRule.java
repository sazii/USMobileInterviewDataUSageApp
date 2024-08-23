package com.usmobile.services.mdnservice.validation.validationRules;

import com.usmobile.services.mdnservice.data.MDNUserInfoDTO;
import com.usmobile.services.mdnservice.exception.MDNCanNotBeEmptyException;
import com.usmobile.shared.model.validation.AbstractCompositeValidator;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MDNCanNotBeEmptyRule extends AbstractCompositeValidator<MDNUserInfoDTO> {

    private final String modifyingType;
    @Override
    public boolean isSatisfiedBy(MDNUserInfoDTO validatable) throws Exception {
        boolean rule = !StringUtils.isEmpty(validatable.getUserId());
        if(!rule){
            throw new MDNCanNotBeEmptyException(modifyingType, validatable.getUserId());
        }
        return true;
    }
}
