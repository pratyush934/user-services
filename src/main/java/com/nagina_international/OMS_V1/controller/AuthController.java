package com.nagina_international.OMS_V1.controller;

import com.nagina_international.OMS_V1.entity.auth.AuthenticationRequest;
import com.nagina_international.OMS_V1.entity.auth.AuthenticationResponse;
import com.nagina_international.OMS_V1.entity.auth.RegisterRequest;
import com.nagina_international.OMS_V1.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/test")
    public String test() {
        return "Test1";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var answer = authenticationService.register(request);
        return new ResponseEntity<>(answer, HttpStatus.OK);
//        System.out.println("So sad it is not happening" + request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {

        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var answer = authenticationService.authenticate(request);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
