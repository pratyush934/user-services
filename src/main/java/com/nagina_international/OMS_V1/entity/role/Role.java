package com.nagina_international.OMS_V1.entity.role;

import com.nagina_international.OMS_V1.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "role")
public class Role {

    /*
    * USER, ADMIN, DISPATCHER, PACKAGER, STOREMANAGER, STOCKMANAGER
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @NotNull
    private String roleName;

    public void setRoleName(String roleName) {
        this.roleName = roleName.toUpperCase();
    }

    public String getRoleName() {
        return roleName.toUpperCase();
    }

    private String Message;


    @ManyToOne
    private User user;
}
