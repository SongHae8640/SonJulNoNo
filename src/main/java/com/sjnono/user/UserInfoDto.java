package com.sjnono.user;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserInfoDto {

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String rePassword;

    @NotNull
    private String email;
}
