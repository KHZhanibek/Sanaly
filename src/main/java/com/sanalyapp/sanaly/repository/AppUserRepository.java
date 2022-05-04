package com.sanalyapp.sanaly.repository;

import com.sanalyapp.sanaly.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public boolean existsByEmail(String email);
    public AppUser getAppUserById(Long appUserId);
}
