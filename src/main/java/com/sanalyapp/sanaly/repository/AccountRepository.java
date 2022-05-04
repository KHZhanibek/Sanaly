package com.sanalyapp.sanaly.repository;

import com.sanalyapp.sanaly.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public List<Account> getAllByUserId(Long appUserId);

    public void deleteByUser_IdAndId(Long appUserId, Long id);

    public boolean existsById(Long id);

    public Account getById(Long id);

    public Account getByUserIdAndId(Long appUserId, Long id);

}
