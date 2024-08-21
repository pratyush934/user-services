package com.nagina_international.OMS_V1.repository.user;

import com.nagina_international.OMS_V1.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmail(String email);

    List<User> getUsersByRoles_RoleName(String roleName);
}
