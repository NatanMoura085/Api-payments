package com.api_payments.domain.service;

import com.api_payments.domain.exceptionhandler.TransactionException;
import com.api_payments.domain.model.Transaction;
import com.api_payments.domain.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Transactional
    public ResponseEntity<Transaction> todasTrasaçoes(Long id){
        return transactionRepository.findById(id).map(ResponseEntity::ok).orElseThrow(()-> new TransactionException("sem transações esse id"));
    }
}
