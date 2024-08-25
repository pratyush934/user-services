package com.nagina_international.OMS_V1.repository.user;

import com.nagina_international.OMS_V1.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> getUsersByRoles_RoleName(String roleName);
}
