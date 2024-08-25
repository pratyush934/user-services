package com.nagina_international.OMS_V1.service;

import com.nagina_international.OMS_V1.entity.auth.AuthenticationRequest;
import com.nagina_international.OMS_V1.entity.auth.AuthenticationResponse;
import com.nagina_international.OMS_V1.entity.auth.RegisterRequest;
import org.springframework.stereotype.Service;


@Service
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
