package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.UserIdentityAvailability;
import com.farmerzharvest.ecom.dto.UserProfile;
import com.farmerzharvest.ecom.exception.AppException;
import com.farmerzharvest.ecom.exception.ResourceNotFoundException;
import com.farmerzharvest.ecom.model.accounts.User;
import com.farmerzharvest.ecom.repository.UserRepository;
import com.farmerzharvest.ecom.security.CurrentUser;
import com.farmerzharvest.ecom.security.UserPrincipal;
import com.farmerzharvest.ecom.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accountService;

    //hasAuthority(‘ROLE_ADMIN') is similar to hasRole(‘ADMIN') because the ‘ROLE_‘ prefix gets added automatically
    @GetMapping("/user/me")
    //@PreAuthorize("hasRole('USER')")
    public UserProfile getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        if(currentUser==null)  {
            throw new AppException("Authorization details are missing/invalid with the request ");
        }

        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new AppException("Failed to get User details"));
        return getUserProfile(user);
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{email}")
    public UserProfile getUserProfile(@PathVariable(value = "email") String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return getUserProfile(user);
    }

    @PutMapping("/update-my-profile")
    @PreAuthorize("hasRole('USER')")
    public UserProfile updateMyProfile(@CurrentUser UserPrincipal currentUser, UserProfile userProfile) {
        userProfile.setId(currentUser.getId());
        return accountService.updateUserProfile(userProfile);
    }

    @PutMapping("/update-profile-by-admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserProfile updateProfileByAdmin(@CurrentUser UserPrincipal currentUser, UserProfile userProfile) {
        return accountService.updateUserProfile(userProfile);
    }

    @GetMapping("/get-existing-profiles")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserProfile> getExistingUserProfiles(@CurrentUser UserPrincipal currentUser, UserProfile userProfile) {
        return accountService.getExistingUserProfiles();
    }

    private UserProfile getUserProfile(User user) {
        return UserProfile.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getId())
                .joinedAt(user.getCreatedAt())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()))
                .username(user.getUsername())
                .build();
    }

}
