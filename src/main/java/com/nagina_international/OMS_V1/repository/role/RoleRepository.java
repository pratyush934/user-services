package com.nagina_international.OMS_V1.repository.role;

import com.nagina_international.OMS_V1.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}
