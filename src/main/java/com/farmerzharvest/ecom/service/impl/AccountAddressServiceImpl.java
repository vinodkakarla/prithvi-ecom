package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.AccountAddressDTO;
import com.farmerzharvest.ecom.entitymapper.AccountAddressResponseMapper;
import com.farmerzharvest.ecom.model.accounts.AccountAddress;
import com.farmerzharvest.ecom.model.accounts.User;
import com.farmerzharvest.ecom.repository.AccountAddressRepository;
import com.farmerzharvest.ecom.repository.UserRepository;
import com.farmerzharvest.ecom.service.AccountAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountAddressServiceImpl implements AccountAddressService {

    private final AccountAddressRepository addressRepository;
    private final UserRepository userRepository;

    private AccountAddressResponseMapper addressResponseMapper;

    @PostConstruct
    public void setup() {
        addressResponseMapper = new AccountAddressResponseMapper();
    }

    @Override
    public List<AccountAddressDTO> getAccountAddressByUser(long userId) {
        return getAddressDTOs(addressRepository.findAllByAccount_id(userId));
    }

    @Override
    public AccountAddressDTO getAccountAddressById(long addressId) {
        return addressResponseMapper.mapToAddressDTO(addressRepository.getOne(addressId));
    }

    @Override
    public List<AccountAddressDTO> getAllAccountAddresses() {
        return getAddressDTOs(addressRepository.findAll());
    }

    @Override
    public AccountAddressDTO saveAccountAddress(AccountAddressDTO addressDTO, boolean isAddRequest) {
        User user = userRepository.getOne(addressDTO.getUserId());
        AccountAddress accountAddress = addressResponseMapper.mapToAccountAddress(addressDTO, user);
        if (isAddRequest) {
            accountAddress.setId(null);
        }
        return addressResponseMapper.mapToAddressDTO(addressRepository.save(accountAddress));
    }

    @Override
    public void deleteAccountAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    private List<AccountAddressDTO> getAddressDTOs(List<AccountAddress> accountAddresses) {
        return accountAddresses.stream()
                .map(addressResponseMapper::mapToAddressDTO)
                .collect(Collectors.toList());
    }


}
