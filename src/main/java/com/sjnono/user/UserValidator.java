package com.sjnono.user;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator {

    public void joinValidate(UserInfoDto userInfoDto, Errors errors){
        if(! userInfoDto.getPassword().equals(userInfoDto.getRePassword())){
            errors.reject("Password mismatch");
        }


    }
}
