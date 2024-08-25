package com.nagina_international.OMS_V1.entity.auth;

import com.nagina_international.OMS_V1.entity.adress.Address;
import com.nagina_international.OMS_V1.entity.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Role> roles;
    private Address address;

}
