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

@SpringBootTest
@ExtendWith(SpringExtension.class)
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

    @Test
    @DisplayName("사용자 등록 후 반환")
    void addAndGet() throws SQLException {
        int id = 1;
        userDao.save(user1);
        UserDto user = userDao.findById(id);
        assertEquals("seoyun", user.getName());
        assertEquals("1234", user.getPassword());
    }
    @Test
    @DisplayName("user 데이터 전체 삭제")
    void deleteAll() {
        userDao.deleteAll();
    }
}