package com.usmobile.shared.model.validation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCompositeValidator<T> implements Validator<T>{
    List<Validator<T>> validatorList;

    public void addValidator(Validator<T> validator){
        if(validatorList == null){
            validatorList = new ArrayList<>();
        }
        validatorList.add(validator);
    }


}
