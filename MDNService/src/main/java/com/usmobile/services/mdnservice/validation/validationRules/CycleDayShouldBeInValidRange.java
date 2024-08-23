package com.usmobile.services.mdnservice.validation.validationRules;

import com.usmobile.services.mdnservice.data.request.SetCycleDayOfMDNRequestDTO;
import com.usmobile.services.mdnservice.exception.CycleDayisNotValidException;
import com.usmobile.shared.model.validation.AbstractCompositeValidator;

public class CycleDayShouldBeInValidRange extends AbstractCompositeValidator<SetCycleDayOfMDNRequestDTO> {

    @Override
    public boolean isSatisfiedBy(SetCycleDayOfMDNRequestDTO validatable) throws Exception {
        int dayOfMonth = validatable.getDayOfMonth();
        var rule = dayOfMonth>0 && dayOfMonth <31;
        if(!rule){
            throw new CycleDayisNotValidException();
        }
        return true;
    }
}
