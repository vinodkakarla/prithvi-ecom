package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.UserProfile;
import com.farmerzharvest.ecom.entitymapper.AccountsMapper;
import com.farmerzharvest.ecom.model.Role;
import com.farmerzharvest.ecom.model.RoleName;
import com.farmerzharvest.ecom.model.accounts.User;
import com.farmerzharvest.ecom.repository.RoleRepository;
import com.farmerzharvest.ecom.repository.UserRepository;
import com.farmerzharvest.ecom.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private AccountsMapper.RequestMapper reqMapper;
    private AccountsMapper.ResponseMapper respMapper;

    @PostConstruct
    public void setup() {
        AccountsMapper mapper = new AccountsMapper();
        reqMapper = mapper.new RequestMapper();
        respMapper = mapper.new ResponseMapper();
    }


    @Override
    public UserProfile updateUserProfile(UserProfile userProfile) {
        User user = reqMapper.apply(userProfile);
        Set<Role> userRoles = user.getRoles();
        addRoleToUser(userRoles, RoleName.ROLE_USER.name());
        for (String roleName : userProfile.getRoles()) {
            addRoleToUser(userRoles, roleName);
        }
        return respMapper.apply(userRepository.save(user));
    }

    @Override
    public List<UserProfile> getExistingUserProfiles() {
        return userRepository.findAll()
                .stream()
                .map(respMapper::apply)
                .collect(Collectors.toList());
    }

    private void addRoleToUser(Set<Role> userRoles, String roleName) {
        try {
            userRoles.add(roleRepository.findByName(RoleName.valueOf(roleName)).get());
        } catch (Exception ex) {
        }
    }
}
