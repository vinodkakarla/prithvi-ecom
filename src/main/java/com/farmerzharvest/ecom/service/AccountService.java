package com.farmerzharvest.ecom.service;

import com.farmerzharvest.ecom.dto.UserProfile;

import java.util.List;

public interface AccountService {

    UserProfile updateUserProfile(UserProfile userProfile);

    List<UserProfile> getExistingUserProfiles();

}
