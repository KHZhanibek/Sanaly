package com.sanalyapp.sanaly.api;

import com.sanalyapp.sanaly.model.Expense;
import com.sanalyapp.sanaly.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users/{user_id}/categories/{category_id}/expenses")
public class CategoryExpenseController<List> {
    ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<?> getAllExpensesOfCategory(@PathVariable(value = "category_id") Long categoryId){
        try {
            return new ResponseEntity<java.util.List<Expense>>(expenseService.getAllCategoryExpenses(categoryId), HttpStatus.OK);
        }
        catch (Error e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getExpenseOfCategory(@PathVariable(value = "id") Long id){
        try {
            return new ResponseEntity<Expense>(expenseService.getCategoryExpense(id), HttpStatus.OK);
        }
        catch (Error e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
