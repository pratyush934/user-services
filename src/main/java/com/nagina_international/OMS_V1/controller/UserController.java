package com.nagina_international.OMS_V1.controller;

import com.nagina_international.OMS_V1.entity.User;
import com.nagina_international.OMS_V1.service.implementation.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/add-user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userServiceImpl.save(user), HttpStatus.OK);
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        User user = userServiceImpl.getUserByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
