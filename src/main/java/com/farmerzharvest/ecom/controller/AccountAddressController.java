package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.AccountAddressDTO;
import com.farmerzharvest.ecom.repository.UserRepository;
import com.farmerzharvest.ecom.security.CurrentUser;
import com.farmerzharvest.ecom.security.UserPrincipal;
import com.farmerzharvest.ecom.service.AccountAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/account-address")
public class AccountAddressController {

    private static final Logger logger = LoggerFactory.getLogger(AccountAddressController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountAddressService addressService;

    @PostMapping("/my-address")
    //@PreAuthorize("hasRole('USER')")
    public AccountAddressDTO addAccountAddress(@CurrentUser UserPrincipal currentUser,
                                               @Valid @RequestBody AccountAddressDTO addressDTO) {
        addressDTO.setUserId(currentUser.getId());
        return addressService.saveAccountAddress(addressDTO, true);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AccountAddressDTO addAccountAddressAdmin(@Valid @RequestBody AccountAddressDTO addressDTO) {
        return addressService.saveAccountAddress(addressDTO, true);
    }

    @PutMapping("/my-address")
    //@PreAuthorize("hasRole('USER')")
    public AccountAddressDTO updateAccountAddress(@CurrentUser UserPrincipal currentUser,
                                                  @Valid @RequestBody AccountAddressDTO addressDTO) {
        addressDTO.setUserId(currentUser.getId());
        return addressService.saveAccountAddress(addressDTO, false);
    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AccountAddressDTO updateAccountAddressAdmin(@Valid @RequestBody AccountAddressDTO addressDTO) {
        return addressService.saveAccountAddress(addressDTO, false);
    }

    @GetMapping("/{addressId}")
    //@PreAuthorize("hasRole('USER')")
    public AccountAddressDTO getAddressById(long addressId) {
        return addressService.getAccountAddressById(addressId);
    }

    @GetMapping("/my-addresses")
    //@PreAuthorize("hasRole('USER')")
    public List<AccountAddressDTO> listMyAddresses(@CurrentUser UserPrincipal currentUser) {
        return addressService.getAccountAddressByUser(currentUser.getId());
    }

    @GetMapping("/list-all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<AccountAddressDTO> listAllAddresses() {
        return addressService.getAllAccountAddresses();
    }

    @DeleteMapping("/{addressId}")
    //@PreAuthorize("hasRole('USER')")
    public void deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAccountAddress(addressId);
    }


}
