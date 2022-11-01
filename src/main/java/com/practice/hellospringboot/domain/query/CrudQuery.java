package com.practice.hellospringboot.domain.query;

public interface CrudQuery {
    String save();

    String findById();

    String deleteAll();

    String getCountAll();
}
