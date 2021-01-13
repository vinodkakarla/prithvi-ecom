package com.farmerzharvest.ecom.service;

import com.farmerzharvest.ecom.dto.AccountAddressDTO;

import java.util.List;

public interface AccountAddressService {
    List<AccountAddressDTO> getAccountAddressByUser(long userId);

    AccountAddressDTO getAccountAddressById(long addressId);

    List<AccountAddressDTO> getAllAccountAddresses();

    AccountAddressDTO saveAccountAddress(AccountAddressDTO addressDTO, boolean isAddRequest);

    void deleteAccountAddress(Long addressId);
}
