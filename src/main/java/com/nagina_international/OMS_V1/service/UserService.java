package com.nagina_international.OMS_V1.service;

import com.nagina_international.OMS_V1.entity.user.User;

public interface UserService {

    User save(User user);

    User getUserByEmail(String email);

}
