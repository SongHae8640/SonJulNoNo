package com.sjnono.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
@SpringBootTest
class UserInfoRepositoryTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    public void showDbConnecionInfo() throws SQLException {
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        System.out.println(metaData.getURL());
        System.out.println(metaData.getDriverName());
        System.out.println(metaData.getUserName());
    }


    @Test
    public void insertUserInfo() {
        UserInfo userInfo = UserInfo.builder()
                .name("Song Hae")
                .password("1234")
                .email("test@naver.com")
                .build();

        UserInfo newUserInfo = userInfoRepository.save(userInfo);
        assertThat(newUserInfo).isNotNull();

        Optional<UserInfo> optUserInfo = userInfoRepository.findById(newUserInfo.getId());
        UserInfo existUserInfo = optUserInfo.get();
        assertThat(existUserInfo).isNotNull();

        UserInfo nonExistUserInfo = userInfoRepository.findByName("hea Song");
        assertThat(nonExistUserInfo).isNull();

    }
}