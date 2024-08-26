package com.nagina_international.OMS_V1.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stockmanager")
public class StockManagerController {

    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_STOCKMANAGER')")
    public String test() {
        return "Test from Stock Manager";
    }
}
