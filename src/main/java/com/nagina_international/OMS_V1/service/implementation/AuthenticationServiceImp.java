package com.nagina_international.OMS_V1.service.implementation;

import com.nagina_international.OMS_V1.entity.auth.AuthenticationRequest;
import com.nagina_international.OMS_V1.entity.auth.AuthenticationResponse;
import com.nagina_international.OMS_V1.entity.auth.RegisterRequest;
import com.nagina_international.OMS_V1.entity.role.Role;
import com.nagina_international.OMS_V1.entity.user.User;
import com.nagina_international.OMS_V1.repository.role.RoleRepository;
import com.nagina_international.OMS_V1.repository.user.UserRepository;
import com.nagina_international.OMS_V1.security.jwt.JwtService;
import com.nagina_international.OMS_V1.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.RequestContextFilter;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    Logger logger = LoggerFactory.getLogger(getClass());


    public AuthenticationServiceImp(JwtService jwtService,
                                    UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager,
                                    RequestContextFilter requestContextFilter,
                                    RoleRepository roleRepository
    ) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {

        //            Role saveRole = roleRepository.findByRoleName(role.getRoleName()).orElseThrow(() -> new RuntimeException("Role not found" + role.getRoleName()));
        Set<Role> roles = new HashSet<>();
        for (Role role : request.getRoles()) {
            Role savedRole = roleRepository.findByRoleName(role.getRoleName())
                    .orElseGet(() -> roleRepository.save(role));
            roles.add(savedRole);
        }

        logger.debug("The roles are {}", roles);

        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .roles(roles)
                .address(request.getAddress())
                .build();

        logger.warn("User: {}", user);

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .jwtToken(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        logger.debug("Printing the details of request {} ", request);
        try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        } catch (Exception e) {
            logger.debug("There are number of issues in the authenticate method {}", e.getMessage());
        }
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not fount with email: " + request.getEmail()));
        String token = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .jwtToken(token)
                .build();
    }
}
