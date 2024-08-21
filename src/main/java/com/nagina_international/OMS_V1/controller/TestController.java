package com.nagina_international.OMS_V1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "Test1";
    }

    @RequestMapping("/test2")
    public String test2() {
        return "Test2";
    }
}
