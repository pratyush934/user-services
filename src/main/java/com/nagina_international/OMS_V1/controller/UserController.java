package com.nagina_international.OMS_V1.controller;

import com.nagina_international.OMS_V1.entity.user.User;
import com.nagina_international.OMS_V1.service.implementation.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/test")
//    @PreAuthorize("hasRole('USER')")
    public String getTestingDone() {
        return "Kaa baat kar rahe hai";
    }
}
