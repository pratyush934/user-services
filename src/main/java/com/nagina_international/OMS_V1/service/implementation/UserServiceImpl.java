package com.nagina_international.OMS_V1.service.implementation;

import com.nagina_international.OMS_V1.entity.User;
import com.nagina_international.OMS_V1.repository.RoleRepository;
import com.nagina_international.OMS_V1.repository.UserRepository;
import com.nagina_international.OMS_V1.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User user= userRepository.getUserByEmail(email);
        System.out.println(user);
        return user;
    }
}
