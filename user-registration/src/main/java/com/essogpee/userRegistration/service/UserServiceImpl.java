package com.essogpee.userRegistration.service;

import com.essogpee.userRegistration.model.AppUser;
import com.essogpee.userRegistration.model.Rol;
import com.essogpee.userRegistration.repository.AppUserRepository;
import com.essogpee.userRegistration.service.dto.UserRegistrationDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final AppUserRepository userRepository;

    public UserServiceImpl(AppUserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public AppUser save(UserRegistrationDTO registrationDTO) {
        AppUser appUser = new AppUser(registrationDTO.getName(),
                registrationDTO.getUserName(),registrationDTO.getEmail(),
                passwordEncoder.encode(registrationDTO.getPassword()), List.of(new Rol("ROLE_USER")));
        return userRepository.save(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByEmail(userName);
        if(appUser == null) {
            throw new UsernameNotFoundException("User password invalid");
        }
        return new User(appUser.getEmail(), appUser.getPassword(), mapAuthoritiesRoles(appUser.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapAuthoritiesRoles(Collection<Rol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public List<AppUser> listUsers() {
        return userRepository.findAll();
    }
}
