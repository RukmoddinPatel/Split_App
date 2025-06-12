package com.split_app.split_app.service;

import com.split_app.split_app.dto.ExpenseDTO;
import com.split_app.split_app.model.Expense;
import com.split_app.split_app.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense addExpense(ExpenseDTO dto) {
        Expense expense = Expense.builder()
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .paidBy(dto.getPaidBy())
                .participants(dto.getParticipants())
                .build();

        return expenseRepository.save(expense);
    }

    public Expense updateExpense(String id, ExpenseDTO dto) {
        Optional<Expense> optional = expenseRepository.findById(id);
        if (optional.isEmpty()) throw new RuntimeException("Expense not found");

        Expense existing = optional.get();
        existing.setDescription(dto.getDescription());
        existing.setAmount(dto.getAmount());
        existing.setPaidBy(dto.getPaidBy());
        existing.setParticipants(dto.getParticipants());

        return expenseRepository.save(existing);
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }

  public List<String> getAllPeople() {
    List<Expense> expenses = expenseRepository.findAll();

    if (expenses == null || expenses.isEmpty()) {
        return new ArrayList<>();
    }

    return expenses.stream()
            .map(Expense::getPaidBy)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet()) // To avoid duplicates
            .stream()
            .toList();
}

}
