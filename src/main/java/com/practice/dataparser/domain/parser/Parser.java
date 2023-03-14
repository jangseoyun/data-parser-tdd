package com.practice.dataparser.domain.parser;

public interface Parser<T> {
    T parse(String str);
}
