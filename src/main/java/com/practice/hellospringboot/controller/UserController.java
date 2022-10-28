package com.practice.hellospringboot.controller;

import com.practice.hellospringboot.dao.UserDao;
import dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserDao userDao;

    @PostMapping("/add")
    public int saveUser(UserDto userDto) {
        log.info("user.controller");
        return userDao.save(userDto);
    }
}
