package com.nagina_international.OMS_V1.entity.adress;

import com.nagina_international.OMS_V1.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

enum AddressType {
    SHIPPING, BILLING, BOTH
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private Long zipCode;
    private String state;
    private String country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

}
