package com.sjnono.bbs;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjnono.user.UserInfo;
import com.sjnono.user.UserInfoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BbsRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BbsRepository bbsRepository;

    @Autowired
    UserInfoRepository userInfoRepository;



    @Test
    public void showDbConnecion() throws SQLException {
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        System.out.println(metaData.getURL());
        System.out.println(metaData.getDriverName());
        System.out.println(metaData.getUserName());
    }


    @Test
    public void insertBbs() {
        UserInfo userInfo = this.userInfoRepository.findById(1L).get();

        Bbs bbs = Bbs.builder()
                .title("Test Title")
                .content("test Content")
                .hits(0)
                .userInfo(userInfo)
                .build();

        Bbs newBbs = bbsRepository.save(bbs);
        assertThat(newBbs).isNotNull();


    }





}