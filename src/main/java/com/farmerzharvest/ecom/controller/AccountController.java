package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.UserIdentityAvailability;
import com.farmerzharvest.ecom.dto.UserProfile;
import com.farmerzharvest.ecom.dto.UserSummary;
import com.farmerzharvest.ecom.exception.ResourceNotFoundException;
import com.farmerzharvest.ecom.model.accounts.User;
import com.farmerzharvest.ecom.repository.UserRepository;
import com.farmerzharvest.ecom.security.CurrentUser;
import com.farmerzharvest.ecom.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary =
                new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
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
        UserProfile userProfile =
                new UserProfile(user.getId(), user.getUsername(), user.getFirstName(), user.getCreatedAt());
        return userProfile;
    }

}
