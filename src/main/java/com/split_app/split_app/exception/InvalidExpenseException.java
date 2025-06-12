package com.split_app.split_app.exception;

public class InvalidExpenseException extends RuntimeException {
    public InvalidExpenseException(String message) {
        super(message);
    }
}
