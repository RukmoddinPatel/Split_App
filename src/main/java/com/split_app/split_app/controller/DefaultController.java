package com.split_app.split_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Expense Splitter API. Use /expenses or /settlements endpoints.";
    }
}
