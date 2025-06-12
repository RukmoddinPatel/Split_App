package com.split_app.split_app.controller;

import com.split_app.split_app.dto.ApiResponse;
import com.split_app.split_app.dto.ExpenseDTO;
import com.split_app.split_app.model.Expense;
import com.split_app.split_app.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    // Get all expenses
@GetMapping
public ResponseEntity<ApiResponse<List<Expense>>> getAllExpenses() {
    List<Expense> expenses = expenseService.getAllExpenses();
    ApiResponse<List<Expense>> response = new ApiResponse<>(true, expenses, "All expenses retrieved successfully");
    return ResponseEntity.ok(response);
}


    // Add a new expense
@PostMapping
public ResponseEntity<ApiResponse<Expense>> addExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
    Expense expense = expenseService.addExpense(expenseDTO);
    ApiResponse<Expense> response = new ApiResponse<>(true, expense, "Expense added successfully");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}


    // Update an expense
@PutMapping("/{id}")
public ResponseEntity<ApiResponse<Expense>> updateExpense(
        @PathVariable String id,
        @Valid @RequestBody ExpenseDTO expenseDTO) {
    Expense updated = expenseService.updateExpense(id, expenseDTO);
    ApiResponse<Expense> response = new ApiResponse<>(true, updated, "Expense updated successfully");
    return ResponseEntity.ok(response);
}


    // Delete an expense
@DeleteMapping("/{id}")
public ResponseEntity<ApiResponse<Void>> deleteExpense(@PathVariable String id) {
    expenseService.deleteExpense(id);
    ApiResponse<Void> response = new ApiResponse<>(true, null, "Expense deleted successfully");
    return ResponseEntity.ok(response);
}


    // Get all unique people involved in expenses
@GetMapping("/people")
public ResponseEntity<ApiResponse<List<String>>> getAllPeople() {
    List<String> people = expenseService.getAllPeople();
    ApiResponse<List<String>> response = new ApiResponse<>(true, people, "People list retrieved successfully");
    return ResponseEntity.ok(response);
}

}
