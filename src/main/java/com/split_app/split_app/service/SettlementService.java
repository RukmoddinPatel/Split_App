package com.split_app.split_app.service;

import com.split_app.split_app.dto.Balance;
import com.split_app.split_app.dto.Settlement;
import com.split_app.split_app.model.Expense;
import com.split_app.split_app.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final ExpenseRepository expenseRepository;

    public List<Balance> getBalances() {
        List<Expense> expenses = expenseRepository.findAll();
        Map<String, Double> balanceMap = new HashMap<>();

        for (Expense expense : expenses) {
            double share = expense.getAmount() / expense.getParticipants().size();
            for (String person : expense.getParticipants()) {
                balanceMap.put(person, balanceMap.getOrDefault(person, 0.0) - share);
            }
            balanceMap.put(expense.getPaidBy(), balanceMap.getOrDefault(expense.getPaidBy(), 0.0) + expense.getAmount());
        }

        List<Balance> balances = new ArrayList<>();
        for (var entry : balanceMap.entrySet()) {
            balances.add(new Balance(entry.getKey(), Math.round(entry.getValue() * 100.0) / 100.0));
        }

        return balances;
    }

    public List<Settlement> getSettlements() {
        List<Balance> balances = getBalances();
        PriorityQueue<Balance> owe = new PriorityQueue<>(Comparator.comparingDouble(Balance::getAmount));
        PriorityQueue<Balance> owed = new PriorityQueue<>((a, b) -> Double.compare(b.getAmount(), a.getAmount()));

        for (Balance b : balances) {
            if (b.getAmount() < 0) owe.add(new Balance(b.getPerson(), -b.getAmount()));
            else if (b.getAmount() > 0) owed.add(b);
        }

        List<Settlement> settlements = new ArrayList<>();

        while (!owe.isEmpty() && !owed.isEmpty()) {
            Balance debtor = owe.poll();
            Balance creditor = owed.poll();

            double minAmount = Math.min(debtor.getAmount(), creditor.getAmount());

            settlements.add(new Settlement(debtor.getPerson(), creditor.getPerson(), Math.round(minAmount * 100.0) / 100.0));

            if (debtor.getAmount() > minAmount) owe.add(new Balance(debtor.getPerson(), debtor.getAmount() - minAmount));
            if (creditor.getAmount() > minAmount) owed.add(new Balance(creditor.getPerson(), creditor.getAmount() - minAmount));
        }

        return settlements;
    }
}
