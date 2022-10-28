package com.practice.hellospringboot.dao;

import dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
public class UserDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(UserDto userDto) {
        return jdbcTemplate.update("insert into users(id, name, password) values (?, ?, ?)"
                , userDto.getId(), userDto.getName(), userDto.getPassword());
    }

    public int deleteAll() {
        return jdbcTemplate.update("delete from users");
    }

}
