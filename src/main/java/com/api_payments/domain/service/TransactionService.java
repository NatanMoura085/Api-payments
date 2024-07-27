package com.api_payments.domain.service;

import com.api_payments.domain.exceptionhandler.TransactionException;
import com.api_payments.domain.model.StatusTransaction;
import com.api_payments.domain.model.Transaction;
import com.api_payments.domain.repository.TransactionRepository;
import com.api_payments.domain.repository.UsuarioLojistaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UsuarioLojistaRepository usuarioLojistaRepository;


    @Transactional
    public ResponseEntity<Transaction> todasTrasaçoes(Long id){
        return transactionRepository.findById(id).map(ResponseEntity::ok).orElseThrow(()-> new TransactionException("sem transações esse id"));
    }
    @Transactional
    public List<Transaction> getAll(){
        return transactionRepository.findBytransactions();
    }

    @Transactional
    public Transaction fazerTransaçao(Transaction transaction){
        if (transaction.equals(transaction.getStatus().equals(StatusTransaction.FAILED))){
          throw new TransactionException("Error na trasaçao faliu");
        }
        if (transaction.getValor().signum() <0){
            throw new TransactionException("o valor tem que ser maior que zero");
        }
        transaction.setDataTransaction(OffsetDateTime.now());




        return transactionRepository.save(transaction);
    }
}
