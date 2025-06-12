package com.split_app.split_app.controller;

import com.split_app.split_app.dto.Balance;
import com.split_app.split_app.dto.Settlement;
import com.split_app.split_app.service.SettlementService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlements")
public class SettlementController {

    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @GetMapping("/balances")
    public ResponseEntity<List<Balance>> getBalances() {
        return ResponseEntity.ok(settlementService.getBalances());
    }

    @GetMapping
    public ResponseEntity<List<Settlement>> getSettlements() {
        return ResponseEntity.ok(settlementService.getSettlements());
    }
}
