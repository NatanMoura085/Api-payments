package com.api_payments.api.controllers;

import com.api_payments.domain.model.Transaction;
import com.api_payments.domain.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("v1/api")
public class TransactionController {
    private final TransactionService transactionService;
    @GetMapping("/transaçoes/{id}")
    public ResponseEntity<Transaction> listaDeTransaçoes(@PathVariable Long id){
        return transactionService.todasTrasaçoes(id);
    }
}
