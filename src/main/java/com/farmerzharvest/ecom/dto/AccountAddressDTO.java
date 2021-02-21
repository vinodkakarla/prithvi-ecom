package com.farmerzharvest.ecom.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountAddressDTO {

    private String name;
    private Long accountAddressId;
    private Long userId;
    private boolean isActive;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private Long pinCode;

}
