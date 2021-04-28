package com.sjnono.user;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserInfoController.class)
//@SpringBootTest
class UserInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DataSource dataSource;

    @MockBean
    UserInfoService userInfoService;

    @MockBean
    UserInfoRepository userInfoRepository;

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
        mockMvc.perform(post("/user"))
                .andDo(print());
    }

    @Test
    @Order(3)
    public void getUserInfoList() throws Exception {

        mockMvc.perform(get("/user"))
                .andDo(print());
    }

}