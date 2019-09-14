package com.sam.backendv2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserContoller {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello";

    }
}
