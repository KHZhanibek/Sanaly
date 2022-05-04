package com.sanalyapp.sanaly.repository;

import com.sanalyapp.sanaly.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
