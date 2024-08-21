package com.nagina_international.OMS_V1.entity.role;

import com.nagina_international.OMS_V1.entity.user.User;
import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String roleName;
    private String Message;

    @ManyToOne
    private User user;
}
