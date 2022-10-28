package com.practice.hellospringboot.dao;

import dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDao.class)
@DisplayName("userDao test")
class UserDaoTest {

    UserDao userDao;
    UserDto user1;
    UserDto user2;
    UserDto user3;

    /*@BeforeEach
    void setup() throws SQLException {
        user1 = new User(1, "seoyun", "1234");
        user2 = new User(2, "seoseo", "1234");
        user3 = new User(3, "yunyun", "1234");
        userDao.deleteAll();
    }

    @Test
    void addAndGet() throws SQLException {
        int id = 1;
        userDao.add(user1);
        User user = userDao.findById(id);
        assertEquals("seoyun", user.getName());
        assertEquals("1234", user.getPassword());
    }*/

}