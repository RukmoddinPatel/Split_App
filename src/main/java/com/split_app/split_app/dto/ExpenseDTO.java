package com.split_app.split_app.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private String description;
    private double amount;
    private String paidBy;
    private List<String> participants; // include this in POST/PUT body
}
