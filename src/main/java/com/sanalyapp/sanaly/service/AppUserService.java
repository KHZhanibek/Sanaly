package com.sanalyapp.sanaly.service;

import com.sanalyapp.sanaly.model.AppUser;
import com.sanalyapp.sanaly.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional

public class AppUserService implements UserDetailsService{
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public String save(AppUser appUser){
        try {
//            hhhhh
            if(appUserRepository.existsByEmail(appUser.getEmail())) return "email already exists";
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            appUserRepository.save(appUser);
            return "User successfully registered";
        }
        catch (Error e){
            return e.getMessage();
        }
    }

    public List<AppUser> getAll(){
        return appUserRepository.findAll();
    }

    public AppUser getById(Long id){
        return appUserRepository.getAppUserById(id);
    }

    public String  deleteById(Long id){
        try {
            appUserRepository.deleteById(id);
            return "user with id:" + id + " was deleted";
        }
        catch (Error e){
            return e.getMessage();
        }
    }

    public String update(Long id, AppUser appUser){
        appUser.setId(id);
        appUserRepository.save(appUser);
        return "user with id:" + id + " was updated";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.getByEmail(email);
        if(appUser == null)
            throw new UsernameNotFoundException("User not found in database");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new User(appUser.getEmail(), appUser.getPassword(), authorities);
    }
}
