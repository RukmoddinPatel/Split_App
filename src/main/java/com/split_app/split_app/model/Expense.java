package com.split_app.split_app.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "expenses")
@Data
public class Expense {
    @Id
    private String id;

    private double amount;
    private String description;
    private String paidBy;

    private List<String> participants = new ArrayList<>();  // ✅ This avoids null
}
