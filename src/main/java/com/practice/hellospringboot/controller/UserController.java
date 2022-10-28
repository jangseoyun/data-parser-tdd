package com.practice.hellospringboot.controller;

import com.practice.hellospringboot.dao.UserDao;
import dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserDao userDao;

    @PostMapping("/add")
    public int saveUser(UserDto userDto) {
        log.info("user.controller.save");
        return userDao.save(userDto);
    }

    @PostMapping("/add2")
    public ResponseEntity<Integer> saveUser2(UserDto userDto) {
        log.info("user.controller.save");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDao.save(userDto));
    }

    @DeleteMapping("/delete-all")
    public int deleteAll() {
        log.info("user.controller.delete-all");
        return userDao.deleteAll();
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUserAll() {
        log.info("user.controller.find-all");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDao.findAll());
    }
}
