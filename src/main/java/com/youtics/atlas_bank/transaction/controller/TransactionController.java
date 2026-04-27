package com.youtics.atlas_bank.transaction.controller;

import com.youtics.atlas_bank.transaction.dto.TransactionRequestDTO;
import com.youtics.atlas_bank.transaction.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody TransactionRequestDTO request) {
        transactionService.deposit(request);
        return ResponseEntity.ok("Depósito realizado con éxito. Saldo actualizado.");
    }
}