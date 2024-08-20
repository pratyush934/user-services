package com.nagina_international.OMS_V1.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;
    private String lastName;

    @Column(unique = true)
    @Email
    @Valid
    private String email;

    @Size(min = 6)
    private String password;

    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private boolean isAcceptingMessage;
    private boolean enabled; //true or false


    @ManyToMany
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

}
