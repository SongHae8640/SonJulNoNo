package com.sjnono.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserInfoServiceTest {

    @Autowired
    UserInfoService userInfoService;

    @Test
    public void findAll(){
        userInfoService.findAll().forEach(user -> System.out.println(user.toString()));
    }

}