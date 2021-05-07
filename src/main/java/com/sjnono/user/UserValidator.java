package com.sjnono.user;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator {

    public void joinValidate(UserInfoDto userInfoDto, Errors errors){
        if(userInfoDto.getName().isEmpty()){
            errors.reject("Name is empty");
        }else if(userInfoDto.getEmail().isEmpty()){
            errors.reject("Email is empty");
        }else if (userInfoDto.getPassword().isEmpty()){
            errors.reject("Password is empty");
        }else if (userInfoDto.getRePassword().isEmpty()){
            errors.reject("RePassword is empty");
        }else if(! userInfoDto.getPassword().equals(userInfoDto.getRePassword())){
            errors.reject("Password mismatch");
        }


    }

    public void loginValidate(UserInfoDto userInfoDto, Errors errors) {
        if(userInfoDto.getName().isEmpty()){
            errors.reject("Name is empty");
        }else if (userInfoDto.getPassword().isEmpty()){
            errors.reject("Password is empty");
        }
    }
}
