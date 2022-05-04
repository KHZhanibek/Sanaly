package com.sanalyapp.sanaly.service;

import com.sanalyapp.sanaly.model.Account;
import com.sanalyapp.sanaly.model.AppUser;
import com.sanalyapp.sanaly.repository.AccountRepository;
import com.sanalyapp.sanaly.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AppUserRepository appUserRepository;

    public Account getAccount(Long id){
        return accountRepository.getById(id);
    }

    public List<Account> getAllAccounts(Long appUserId){
        return accountRepository.getAllByUserId(appUserId);
    }

    public List<Account> getAllAppUserAccountsByAppUserId(Long appUserId){
        List<Account> accounts  = accountRepository.getAllByUserId(appUserId);
        return accounts;
    }

    public Account getAppUserAccountById(Long appUserId, Long id) throws NotFoundException {
        Account account = accountRepository.getByUserIdAndId(appUserId, id);
        if(account.getUser().getId().equals(appUserId))
            return account;
        else
            throw new NotFoundException();
    }

    public String saveAccount(Account account){
        try {
            return accountRepository.save(account).getTitle() + " was created";
        }
        catch (Error e){
            return e.getMessage();
        }
    }

    public String saveAppUserAccount(Long appUserId, Account account){
        try {
            AppUser appUser = appUserRepository.getById(appUserId);
            account.setUser(appUser);
            System.out.println("saveAppUserAccount()");
            accountRepository.getAllByUserId(appUserId).forEach(account1 ->
                    System.out.println(account1.toString()));
            if (accountRepository.getAllByUserId(appUserId).size() == 3) {
                return "User " + appUser.getFirstName() + " " + appUser.getLastName() +
                        " already have 3 accounts, for unlimited number of accounts you should upgrade to pro version!";
            }
            accountRepository.save(account);
            return account.getUser().getFirstName() + " " + account.getUser().getLastName() +
                    " have another account " + account.getTitle() + "!";
        }
        catch (Error e){
            return e.getMessage();
        }
    }

    @Transactional
    public String deleteAppUserAccountById(Long appUserId, Long id){
        try {
            Account account = accountRepository.getById(id);
            if(account == null)
                return "No such account id in database!";
            if(!account.getUser().getId().equals(appUserId))
                return "NOT PERMITTED to delete this account!";
            accountRepository.deleteByUser_IdAndId(appUserId, id);
            return "Successfully deleted account";
        }
        catch (Error e){
            return e.getMessage();
        }
    }

    public String updateAppUserAccount(Long appUserId, Long id, Account account){
        try {
            AppUser appUser = appUserRepository.getAppUserById(appUserId);
            if(appUser == null)
                return "No such user id in database!";
            account.setUser(appUser);
            account.setId(id);
            accountRepository.save(account);
            return "Successfully updated account";
        }
        catch (Error e){
            return e.getMessage();
        }
    }

}
