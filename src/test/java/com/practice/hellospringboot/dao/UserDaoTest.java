package com.practice.hellospringboot.dao;

import dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserDao userDao;

    UserDto user1;
    UserDto user2;
    UserDto user3;

    @BeforeEach
    void setup() throws SQLException {
        user1 = new UserDto(1, "seoyun", "1234");
        user2 = new UserDto(2, "seoseo", "1234");
        user3 = new UserDto(3, "yunyun", "1234");
        userDao.deleteAll();
    }

}