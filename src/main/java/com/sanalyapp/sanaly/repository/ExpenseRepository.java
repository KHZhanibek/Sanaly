package com.sanalyapp.sanaly.repository;

import com.sanalyapp.sanaly.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

//  Account and Expense
    List<Expense> getAllByAccountId(Long accountId);
    List<Expense> findAllByAccount_Id(Long accountId);
    Expense getByAccountId(Long accountId);
    void deleteByAccountIdAndId(Long accountId, Long id);

//  Category and Expense
    List<Expense> getAllByCategoryId(Long categoryId);
    Expense getByCategoryId(Long categoryId);
    void  deleteByCategoryIdAndId(Long categoryId, Long id);

//

}
