package com.sjnono.bbs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjnono.user.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BbsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Autowired
    BbsService bbsService;



    @BeforeEach
    void setup(){


    }

    @Test
    void showBbsList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/bbs"));

        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("bbs/bbs_list"))
                .andExpect(model().attributeExists("bbsList"))
                ;



    }

    @Test
    void showBbsDetail() throws Exception{
        ResultActions actions = mockMvc.perform(get("/bbs/1"));

        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bbs"))
        ;

    }
    @Test
    void writeBbs() throws Exception{
        Bbs bbs = Bbs.builder()
                .title("테스트 제목")
                .content("내용 내용")
                .userInfo(UserInfo.builder().id(1L).build())
                .build();

        ResultActions actions = mockMvc.perform(post("/bbs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bbs))
        );


        actions.andDo(print())
                .andExpect(status().isFound())
                ;

    }

    @Test
    void deleteBbs() throws Exception{
        ResultActions actions = mockMvc.perform(delete("/bbs/5"));

        actions.andDo(print())
                ;
    }


    @Test
    void editBbs() throws Exception{
        Bbs bbs = Bbs.builder()
                .id(1L)
                .title("테스트 제목(수정)")
                .content("내용 내용(수정)")
                .build();

        ResultActions actions = mockMvc.perform(put("/bbs/"+bbs.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bbs))
        );


        actions.andDo(print())
                .andExpect(status().isFound())
        ;

    }





}