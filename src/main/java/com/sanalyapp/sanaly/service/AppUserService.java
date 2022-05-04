package com.sanalyapp.sanaly.service;

import com.sanalyapp.sanaly.model.AppUser;
import com.sanalyapp.sanaly.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public String save(AppUser appUser){
        try {
            if(appUserRepository.existsByEmail(appUser.getEmail())) return "email already exists";
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
}
