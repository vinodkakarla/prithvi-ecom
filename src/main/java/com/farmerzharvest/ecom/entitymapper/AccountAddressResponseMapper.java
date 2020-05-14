package com.farmerzharvest.ecom.entitymapper;

import com.farmerzharvest.ecom.dto.AccountAddressDTO;
import com.farmerzharvest.ecom.model.accounts.AccountAddress;
import com.farmerzharvest.ecom.model.accounts.User;

public class AccountAddressResponseMapper {

    public AccountAddressDTO mapToAddressDTO(AccountAddress accountAddress) {
        return AccountAddressDTO.builder()
                .userId(accountAddress.getAccount().getId())
                .accountAddressId(accountAddress.getId())
                .addressLine1(accountAddress.getAddressLine1())
                .addressLine2(accountAddress.getAddressLine2())
                .city(accountAddress.getCity())
                .pinCode(accountAddress.getPinCode())
                .state(accountAddress.getState())
                .build();
    }

    public AccountAddress mapToAccountAddress(AccountAddressDTO addressDTO, User account) {
        return AccountAddress.builder()
                .account(account)
                .id(addressDTO.getAccountAddressId())
                .addressLine1(addressDTO.getAddressLine1())
                .addressLine2(addressDTO.getAddressLine2())
                .city(addressDTO.getCity())
                .pinCode(addressDTO.getPinCode())
                .state(addressDTO.getState())
                .build();
    }
}
