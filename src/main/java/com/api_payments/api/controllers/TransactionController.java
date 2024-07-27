package com.api_payments.api.controllers;

import com.api_payments.domain.model.Transaction;
import com.api_payments.domain.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/api")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/transaçoes")
    public List<Transaction> transactionList(){
        return transactionService.getAll();
    }
    @GetMapping("/transaçoes/{id}")
    public ResponseEntity<Transaction> listaDeTransaçoes(@PathVariable Long id){
        return transactionService.todasTrasaçoes(id);
    }
    @PostMapping("/transaçoes")
    public Transaction cadastrarTrnsaçao(@RequestBody Transaction transaction){
        return transactionService.fazerTransaçao(transaction);
    }
}
