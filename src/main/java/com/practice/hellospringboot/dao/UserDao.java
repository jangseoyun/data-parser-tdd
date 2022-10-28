package com.practice.hellospringboot.dao;

import dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class UserDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public int save(UserDto userDto) {
        return jdbcTemplate.update("insert into users(id, name, password) values (?, ?, ?)"
                , userDto.getId(), userDto.getName(), userDto.getPassword());
    }

}
