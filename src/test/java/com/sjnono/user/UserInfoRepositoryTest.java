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

//@ExtendWith(SpringExtension.class)
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
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Song Hae");
        userInfo.setPassword("1234");
        userInfo.setEmail("test@naver.com");

        UserInfo newUserInfo = userInfoRepository.save(userInfo);
        assertThat(newUserInfo).isNotNull();

        Optional<UserInfo> byId = userInfoRepository.findById(newUserInfo.getId());
        UserInfo existUserInfo = byId.get();
        assertThat(existUserInfo).isNotNull();

        UserInfo nonExistUserInfo = userInfoRepository.findByName("hea Song");
        assertThat(nonExistUserInfo).isNull();

    }
}