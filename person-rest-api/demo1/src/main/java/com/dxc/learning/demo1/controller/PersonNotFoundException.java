package com.dxc.learning.demo1.controller;

public class PersonNotFoundException extends RuntimeException {
    PersonNotFoundException(Integer id) {
        super("Could not find Person " + id);

    }
}