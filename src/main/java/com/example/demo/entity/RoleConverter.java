package com.example.demo.entity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String value) {
        return Role.valueOf(value.toUpperCase());
    }
}