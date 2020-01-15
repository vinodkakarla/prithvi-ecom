package com.farmerzharvest.ecom.model.accounts;

import com.farmerzharvest.ecom.model.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
public class AccountAddress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private User account;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private Long pinCode;
}
