package com.split_app.split_app.repository;

import com.split_app.split_app.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
}
