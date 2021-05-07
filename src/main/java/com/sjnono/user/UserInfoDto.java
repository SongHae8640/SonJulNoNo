package com.sjnono.user;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserInfoDto {

    private String name;
    private String password;
    private String rePassword;
    private String email;
}
