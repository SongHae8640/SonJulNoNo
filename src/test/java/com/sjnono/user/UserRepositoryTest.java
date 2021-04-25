package com.sjnono.user;

import com.sjnono.bbs.Bbs;
import com.sjnono.bbs.BbsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    public void showDbConnecionInfo() throws SQLException {
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        System.out.println(metaData.getURL());
        System.out.println(metaData.getDriverName());
        System.out.println(metaData.getUserName());
    }


    @Test
    public void insertBbs() {
        User user = new User();
        user.setName("Song Hae");
        user.setPassword("1234");
        user.setEmail("test@naver.com");

        User newUser = userRepository.save(user);
        assertThat(newUser).isNotNull();

        User existUser = userRepository.findByName(newUser.getName());
        assertThat(userRepository).isNotNull();

        User nonExistUser = userRepository.findByName("hea Song");
        assertThat(nonExistUser).isNull();

    }
}