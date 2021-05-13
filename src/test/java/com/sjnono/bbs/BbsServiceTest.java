package com.sjnono.bbs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BbsServiceTest {

    @Autowired
    BbsService bbsService;

    @Test
    public void getBbs() throws Exception{
        Bbs bbs = this.bbsService.findByIdFetchJoin(1l);


    }

}