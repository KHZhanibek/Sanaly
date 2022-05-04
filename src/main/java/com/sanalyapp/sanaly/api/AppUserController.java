package com.sanalyapp.sanaly.api;

import com.sanalyapp.sanaly.model.AppUser;
import com.sanalyapp.sanaly.model.Expense;
import com.sanalyapp.sanaly.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> getAllAppUsers(){
        return appUserService.getAll();
    }

    @GetMapping(value = "/{id}")
    public AppUser getAppUserById(@PathVariable(value = "id") Long id){
        return appUserService.getById(id);
    }

    @PostMapping
    public String createAppUser(@RequestBody AppUser appUser){
        return appUserService.save(appUser);
    }

    @PutMapping(value = "/{id}")
    public String updateAppUser(@PathVariable(value = "id") Long id, @RequestBody AppUser appUser){
        return appUserService.update(id, appUser);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteAppUser(@PathVariable(value = "id") Long id){
        return appUserService.deleteById(id);
    }

}
