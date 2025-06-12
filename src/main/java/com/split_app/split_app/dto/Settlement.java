// Settlement.java
package com.split_app.split_app.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class Settlement {
    private String from;
    private String to;
    private double amount;
}
