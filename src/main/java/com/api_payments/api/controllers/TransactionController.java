package com.api_payments.api.controllers;

import com.api_payments.api.assembler.Assembler;
import com.api_payments.api.dto.TransactionDTO;
import com.api_payments.api.dto.inputDTO.TransactionInputDTO;
import com.api_payments.domain.model.Transaction;
import com.api_payments.domain.model.UsuarioComum;
import com.api_payments.domain.model.UsuarioLojista;
import com.api_payments.domain.service.TransactionService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/api")
public class TransactionController {
    private final TransactionService transactionService;
    private final Assembler assembler;

    @GetMapping("/transaçoes")
    public List<TransactionDTO> transactionList(){
        return assembler.toMapCollectionTransactions(transactionService.getAll());
    }
    @GetMapping("/transaçoes/{id}")
    public ResponseEntity<Transaction> listaDeTransaçoes(@PathVariable Long id){
        return transactionService.todasTrasaçoes(id);
    }
    @PostMapping("/transaçoes")
    public TransactionDTO cadastrarTrnsaçao(@RequestBody TransactionInputDTO transactionInputDTO, @RequestParam Long receivedid_id, @RequestParam Long senderid) throws MessagingException {
        Transaction transaction = assembler.toEntityTransactionINput(transactionInputDTO);
        return assembler.convertEntityDTO(transactionService.fazerTransaçao(transaction,receivedid_id,senderid));
    }
}
