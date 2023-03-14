package com.practice.dataparser.dao;

import dto.TableInfoDto;
import dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public UserDto findById(int id) {
        RowMapper<UserDto> rowMapper = new RowMapper<>() {
            @Override
            public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserDto getUser = new UserDto(rs.getInt("id")
                        , rs.getString("name")
                        , rs.getString("password"));
                return getUser;
            }
        };

        return jdbcTemplate.queryForObject("select * from users where id = ? ", rowMapper, id);
    }

    public List<UserDto> findAll() {
        RowMapper<UserDto> rowMapper = new RowMapper<>() {
            @Override
            public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserDto user = new UserDto(rs.getInt("id")
                        , rs.getString("name")
                        , rs.getString("password"));
                return user;
            }
        };
       return jdbcTemplate.query("select * from users", rowMapper);
    }

    public int getCountAll() {
        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public TableInfoDto getTableInfo() {
        List<UserDto> userAll = findAll();
        return new TableInfoDto(userAll.size(), userAll);
    }



}
