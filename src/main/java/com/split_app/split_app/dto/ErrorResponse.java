package com.split_app.split_app.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}