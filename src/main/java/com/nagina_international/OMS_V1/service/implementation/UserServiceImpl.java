package com.nagina_international.OMS_V1.service.implementation;

import com.nagina_international.OMS_V1.entity.user.User;
import com.nagina_international.OMS_V1.repository.role.RoleRepository;
import com.nagina_international.OMS_V1.repository.user.UserRepository;
import com.nagina_international.OMS_V1.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<User> user= userRepository.findByEmail(email);
        System.out.println(user);
        return user.orElse(null);
    }
}
