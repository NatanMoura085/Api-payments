package com.api_payments.domain.service;

import com.api_payments.domain.exceptionhandler.TransactionException;
import com.api_payments.domain.model.StatusTransaction;
import com.api_payments.domain.model.Transaction;
import com.api_payments.domain.model.UsuarioComum;
import com.api_payments.domain.model.UsuarioLojista;
import com.api_payments.domain.repository.TransactionRepository;
import com.api_payments.domain.repository.UsuarioComumRepository;
import com.api_payments.domain.repository.UsuarioLojistaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UsuarioLojistaRepository usuarioLojistaRepository;
    private final UsuarioComumRepository usuarioComumRepository;
    @Transactional
    public void calcularSubs(BigDecimal valor,Long lojistaId,Long usuarioId,UsuarioComum usuarioComum,UsuarioLojista usuarioLojista) {
       Optional<UsuarioComum> usuarioIDConsult = usuarioComumRepository.findById(usuarioId);
        Optional<UsuarioLojista> lojistaIDConsult = usuarioLojistaRepository.findById(lojistaId);

        if (usuarioIDConsult.isPresent() && lojistaIDConsult.isPresent()){
            UsuarioComum usuarioComum1 =usuarioIDConsult.get();
            UsuarioLojista usuarioLojista1 = lojistaIDConsult.get();
            usuarioComum1.setSaldoConta(usuarioComum1.getSaldoConta().subtract(valor));
            usuarioLojista1.setSaldoConta(usuarioLojista1.getSaldoConta().add(valor));

        }
        usuarioComum.setId(usuarioId);
        usuarioLojista.setId(lojistaId);

        UsuarioComum usuarioComum1 = usuarioComumRepository.save(usuarioComum);
        UsuarioLojista usuarioLojista1 = usuarioLojistaRepository.save(usuarioLojista);

    }

    @Transactional
    public ResponseEntity<Transaction> todasTrasaçoes(Long id){
        return transactionRepository.findById(id).map(ResponseEntity::ok).orElseThrow(()-> new TransactionException("sem transações esse id"));
    }
    @Transactional
    public List<Transaction> getAll(){
        return transactionRepository.findBytransactions();
    }

    @Transactional
    public Transaction fazerTransaçao(Transaction transaction,Long lojistaId,Long usuarioId){

        Optional<UsuarioComum> optionalUsuarioComum = usuarioComumRepository.findById(usuarioId);
        UsuarioComum usuarioComum = optionalUsuarioComum.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

      Optional<UsuarioLojista> OPtionalusuarioLojista = usuarioLojistaRepository.findById(lojistaId);
      UsuarioLojista usuarioLojista = OPtionalusuarioLojista.orElseThrow(()-> new TransactionException("lojista nao encontrado"));
       calcularSubs(transaction.getValor(),lojistaId,usuarioId, usuarioComum,usuarioLojista);
        if (transaction.equals(transaction.getStatus().equals(StatusTransaction.FAILED))){
          throw new TransactionException("Error na trasaçao faliu");
        }
        if (transaction.getValor().signum() <0){
            throw new TransactionException("o valor tem que ser maior que zero");
        }
        transaction.setDataTransaction(OffsetDateTime.now());
        transaction.setSenderID(usuarioComum);
        transaction.setReceivedID(usuarioLojista);



        return transactionRepository.save(transaction);
    }
}
