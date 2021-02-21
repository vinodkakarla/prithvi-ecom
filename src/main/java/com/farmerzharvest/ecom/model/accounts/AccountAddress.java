package com.farmerzharvest.ecom.model.accounts;

import com.farmerzharvest.ecom.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountAddress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private User account;

    private String name;
    private boolean isActive;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private Long pinCode;
}
