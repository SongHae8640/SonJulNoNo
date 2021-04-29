package com.sjnono.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(UserInfoController.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DataSource dataSource;


    @Test
    @Order(1)
    public void showDbConnecionInfo() throws SQLException {
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        System.out.println(metaData.getURL());
        System.out.println(metaData.getDriverName());
        System.out.println(metaData.getUserName());
    }

    @Test
    @Order(2)
    public void insertUserInfo() throws Exception{
        //test 데이터
        UserInfo userInfo = UserInfo.builder()
                .id(0L)
                .email("test@gmail.com")
                .name("Song")
                .password("1234")
                .build();


        ResultActions actions = mockMvc.perform(post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userInfo)))
                            ;

        actions.andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/1"));
    }

    @Test
    @Order(3)
    public void getUserInfoList() throws Exception {

        mockMvc.perform(get("/user"))
                .andDo(print());
    }

    @Test
    @Order(3)
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