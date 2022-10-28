package com.practice.hellospringboot.dao;

import dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public int save(UserDto userDto) {
        return jdbcTemplate.update("insert into users(id, name, password) values (?, ?, ?)"
                , userDto.getId(), userDto.getName(), userDto.getPassword());
    }

    public int deleteAll() {
        return jdbcTemplate.update("delete from users");
    }

}
