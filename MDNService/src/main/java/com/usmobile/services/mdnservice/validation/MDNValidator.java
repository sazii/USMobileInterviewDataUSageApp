package com.usmobile.services.mdnservice.validation;


import com.usmobile.services.mdnservice.constants.MDNServiceConstants;
import com.usmobile.services.mdnservice.data.MDNUserInfoDTO;
import com.usmobile.services.mdnservice.data.request.AddMDNRequestDTO;
import com.usmobile.services.mdnservice.data.request.SetCycleDayOfMDNRequestDTO;
import com.usmobile.services.mdnservice.validation.validationRules.CycleDayShouldBeInValidRange;
import com.usmobile.services.mdnservice.validation.validationRules.MDNCanNotBeEmptyRule;
import com.usmobile.services.mdnservice.validation.validationRules.UserIdCanNotBeEmptyRule;
import com.usmobile.shared.model.validation.AndValidator;
import com.usmobile.shared.model.validation.Validator;
import org.springframework.stereotype.Service;

@Service
public class MDNValidator {
    public boolean runValidationsForAddingMDN(AddMDNRequestDTO requestDTO){
        return validateMDNUserInfoDTO(requestDTO.getMdnUserInfoDTO());
    }

    public boolean runValidationsForSetCycleDay(SetCycleDayOfMDNRequestDTO requestDTO){
        AndValidator<SetCycleDayOfMDNRequestDTO> validatorList = new AndValidator<>();
        validatorList.addValidator(new CycleDayShouldBeInValidRange());
        return validateMDNUserInfoDTO(requestDTO.getMdnUserInfoDTO()) && validatorList.isSatisfiedBy(requestDTO) ;
    }

    public boolean validateMDNUserInfoDTO(MDNUserInfoDTO mdnUserInfoDTO){
        AndValidator<MDNUserInfoDTO> validatorList = new AndValidator<>();
        validatorList.addValidator(new UserIdCanNotBeEmptyRule(MDNServiceConstants.ModifyingType.ADD));
        validatorList.addValidator(new MDNCanNotBeEmptyRule(MDNServiceConstants.ModifyingType.ADD));
        return validatorList.isSatisfiedBy(mdnUserInfoDTO);
    }
}
