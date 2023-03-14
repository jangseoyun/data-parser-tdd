package com.practice.dataparser.domain.query;

public interface CrudQuery {
    String save();

    String findById();

    String deleteAll();

    String getCountAll();

    String findAll();
}
