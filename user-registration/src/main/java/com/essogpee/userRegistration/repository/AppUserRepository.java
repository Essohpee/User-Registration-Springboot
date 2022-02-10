package com.essogpee.userRegistration.repository;

import com.essogpee.userRegistration.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
     AppUser findByEmail(String email);
}
