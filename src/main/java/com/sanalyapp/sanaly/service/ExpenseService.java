package com.sanalyapp.sanaly.service;

import com.sanalyapp.sanaly.model.Account;
import com.sanalyapp.sanaly.model.Category;
import com.sanalyapp.sanaly.model.Expense;
import com.sanalyapp.sanaly.repository.AccountRepository;
import com.sanalyapp.sanaly.repository.CategoryRepository;
import com.sanalyapp.sanaly.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Service
public class ExpenseService {
    CategoryRepository categoryRepository;
    AccountRepository accountRepository;
    ExpenseRepository expenseRepository;

    public List<Expense> getAllAccountExpenses(Long accountId){
        return expenseRepository.getAllByAccountId(accountId);
    }

    public Expense getAccountExpense(Long id){
        return expenseRepository.getById(id);
    }

    public List<Expense> getAllCategoryExpenses(Long accountId){
        return expenseRepository.getAllByCategoryId(accountId);
    }

    public Expense getCategoryExpense(Long id){
        return expenseRepository.getByCategoryId(id);
    }

    public String saveExpense(Long userId, Long accountId, String categoryTitle, Expense expense) throws NullPointerException{
        Category category = categoryRepository.getByUserIdAndTitle(userId, categoryTitle);
        if(category == null)
            throw new NullPointerException("No such category in Database");
        Account account = accountRepository.getById(accountId);
        expense.setAccount(account);
        expense.setCategory(category);
        expenseRepository.save(expense);
        return "Successfully saved Expense " + expense.toString();
    }

    public String updateExpense(Long accountId, Expense expense){
        Account account = accountRepository.getById(accountId);
        expense.setAccount(account);
        expenseRepository.save(expense);
        return "Successfully updated Expense " + expense.toString();
    }

    public String deleteExpense(Long expenseId){
        expenseRepository.deleteById(expenseId);
        return "Successfully deleted Expense";
    }


}
