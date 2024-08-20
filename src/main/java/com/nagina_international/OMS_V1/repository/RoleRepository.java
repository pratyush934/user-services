package com.nagina_international.OMS_V1.repository;

import com.nagina_international.OMS_V1.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
//    Optional<Role> findByRoleName(RoleType roleType);
}
