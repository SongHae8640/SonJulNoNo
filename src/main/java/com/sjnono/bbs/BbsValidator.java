package com.sjnono.bbs;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Objects;

@Component
public class BbsValidator {

    public void writeValidate(Bbs bbs, Errors errors){
        if(bbs.getTitle().isEmpty()){
            errors.reject("Title is empty");
        }else if(bbs.getContent().isEmpty()){
            errors.reject("Content is empty");
        }else if (Objects.isNull(bbs.getUserInfo()) ){
            errors.reject("UserInfo is empty");
        }


    }

    public void editeValidate(Bbs bbs, Errors errors) {
        if(bbs.getTitle().isEmpty()){
            errors.reject("Title is empty");
        }else if(bbs.getContent().isEmpty()){
            errors.reject("Content is empty");
        }
    }
}
