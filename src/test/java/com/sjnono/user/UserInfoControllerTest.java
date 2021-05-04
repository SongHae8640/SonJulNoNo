package com.sjnono.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(UserInfoController.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;



    @Test
    public void joinNewUserInfo() throws Exception{
        //신규 데이터
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .email("test@gmail.com")
                .name("Song")
                .password("1234")
                .rePassword("1234")
                .build();


        ResultActions actions = mockMvc.perform(post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userInfoDto)))
                            ;

        actions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("http://localhost/user/3"));
    }

    @Test
    public void joinExistUserInfo() throws Exception{
        //기존 데이터
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .email("1@naver.com")   //회원 가입한 email
                .name("Song")
                .password("1234")
                .rePassword("1234")
                .build();


        ResultActions actions = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userInfoDto)))
                ;

        actions.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(redirectedUrl("http://localhost/user/3"));
    }

    @Test
    public void getUserInfoList() throws Exception {

        mockMvc.perform(get("/user"))
                .andDo(print());
    }

    @Test
    public void getUserInfo() throws Exception{
        ResultActions actions = mockMvc.perform(get("/user/{id}", 1));

        actions.andExpect(status().isOk())
                .andExpect(model().attribute("userInfo", hasProperty("id" , is(1L))))
                .andExpect(model().attribute("userInfo", hasProperty("email" , is("test@gmail.com"))))
                .andExpect(model().attribute("userInfo", hasProperty("name" , is("Song"))))
                .andExpect(model().attribute("userInfo", hasProperty("password" , is("1234"))))
                ;
    }

}