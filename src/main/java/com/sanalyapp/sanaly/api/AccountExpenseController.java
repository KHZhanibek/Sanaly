package com.sanalyapp.sanaly.api;

import com.sanalyapp.sanaly.model.Expense;
import com.sanalyapp.sanaly.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor

@RestController
@RequestMapping("/api/v1/users/{user_id}/accounts/{account_id}/expenses")
public class AccountExpenseController {
    ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<?> getAllExpensesOfAccount(@PathVariable(value = "account_id") Long accountId){
        System.out.println("Account id is: " + accountId);
        try {
            return new ResponseEntity<List<Expense>>(expenseService.getAllAccountExpenses(accountId), HttpStatus.OK);
        }
        catch (NullPointerException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getExpenseOfAccount(@PathVariable(value = "id") Long id){
        try {
            return new ResponseEntity<Expense>(expenseService.getAccountExpense(id), HttpStatus.OK);
        }
        catch (Error e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveExpense(@PathVariable(value = "account_id") Long accountId,
                                              @PathVariable(value = "user_id") Long userId,
                                              @RequestBody Map<String, String> reqBody){
//        System.out.println(reqBody.entrySet());
//        return "Category: " ;
        String categoryTitle = reqBody.get("category_name");
        Expense expense = new Expense(null, reqBody.get("title"), Double.parseDouble(reqBody.get("cash")), null, null);
        System.out.println(categoryTitle + " " + expense.toString());
//        return new ResponseEntity<String>(categoryTitle + " " + expense.toString(), HttpStatus.OK);
        try {
            return new ResponseEntity<String>(expenseService.saveExpense(userId, accountId, categoryTitle, expense), HttpStatus.OK);
        }
        catch (Error e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "{/id}")
    public ResponseEntity<?> updateExpense(@PathVariable(value = "account_id") Long accountId,
                                           @RequestBody Expense expense){
        try {
            return new ResponseEntity<String>(expenseService.updateExpense(accountId, expense), HttpStatus.OK);
        }
        catch (Error e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "{/id}")
    public ResponseEntity<?> deleteExpense(@PathVariable(value = "id") Long id){
        try {
            return new ResponseEntity<String>(expenseService.deleteExpense(id), HttpStatus.OK);
        }
        catch (Error e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
