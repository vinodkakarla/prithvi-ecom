package com.farmerzharvest.ecom.entitymapper;

import com.farmerzharvest.ecom.dto.UserProfile;
import com.farmerzharvest.ecom.model.accounts.User;
import com.google.common.collect.Sets;

import java.util.function.Function;
import java.util.stream.Collectors;

public class AccountsMapper {

    public class ResponseMapper implements Function<User, UserProfile> {

        public UserProfile apply(User user) {
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

    public class RequestMapper implements Function<UserProfile, User> {

        @Override
        public User apply(UserProfile request) {
            User user = new User();
            user.setId(request.getId());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setRoles(Sets.newHashSet());
            return user;
        }
    }
}
