package com.sanalyapp.sanaly.api;

import com.sanalyapp.sanaly.model.Account;
import com.sanalyapp.sanaly.repository.AccountRepository;
import com.sanalyapp.sanaly.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/api/v1/users/{user_id}/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccountsOfAppUser(@PathVariable(value = "user_id") Long appUserId){
        try {
            return new ResponseEntity<>(accountService.getAllAppUserAccountsByAppUserId(appUserId), HttpStatus.OK);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAccountOfAppUser(@PathVariable(value = "user_id") Long appUserId, @PathVariable(value = "id") Long id){
        try {
            return new ResponseEntity<>(accountService.getAppUserAccountById(appUserId, id), HttpStatus.OK);
        }
        catch (IllegalStateException | NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public String createAccountOfAppUser(@PathVariable(value = "user_id") Long appUserId, @RequestBody Account account){
        return accountService.saveAppUserAccount(appUserId, account);
    }

    @PutMapping(value = "/{id}")
    public String updateAccountOfAppUser(@PathVariable(value = "user_id") Long appUserId, @PathVariable(value = "id") Long id, @RequestBody Account account){
        return accountService.updateAppUserAccount(appUserId, id, account);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteAccountOfAppUser(@PathVariable(value = "user_id") Long appUserId, @PathVariable(value = "id") Long id){
        return accountService.deleteAppUserAccountById(appUserId, id);
    }



}
