package com.essogpee.userRegistration.service;

import com.essogpee.userRegistration.model.AppUser;
import com.essogpee.userRegistration.service.dto.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    AppUser save(UserRegistrationDTO registrationDTO);

    List<AppUser> listUsers();
}
