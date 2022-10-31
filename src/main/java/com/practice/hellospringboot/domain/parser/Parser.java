package com.practice.hellospringboot.domain.parser;

public interface Parser<T> {
    T parse(String str);
}
