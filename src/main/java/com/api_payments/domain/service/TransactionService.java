package com.api_payments.domain.service;

import com.api_payments.domain.exceptionhandler.TransactionException;
import com.api_payments.domain.model.StatusTransaction;
import com.api_payments.domain.model.Transaction;
import com.api_payments.domain.model.UsuarioComum;
import com.api_payments.domain.model.UsuarioLojista;
import com.api_payments.domain.repository.TransactionRepository;
import com.api_payments.domain.repository.UsuarioComumRepository;
import com.api_payments.domain.repository.UsuarioLojistaRepository;
import jakarta.mail.MessagingException;
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
    private final EmailService emailService;

    @Transactional
    public void calcularSubs(BigDecimal valor, Long lojistaId, Long usuarioId) {
        Optional<UsuarioComum> usuarioIDConsult = usuarioComumRepository.findById(usuarioId);
        Optional<UsuarioLojista> lojistaIDConsult = usuarioLojistaRepository.findById(lojistaId);

        if (usuarioIDConsult.isPresent() && lojistaIDConsult.isPresent()) {
            UsuarioComum usuarioComum1 = usuarioIDConsult.get();
            UsuarioLojista usuarioLojista1 = lojistaIDConsult.get();
            usuarioComum1.setId(usuarioId);
            usuarioLojista1.setId(lojistaId);
            usuarioComum1.setSaldoConta(usuarioComum1.getSaldoConta().subtract(valor));
            usuarioLojista1.setSaldoConta(usuarioLojista1.getSaldoConta().add(valor));
            usuarioComumRepository.save(usuarioComum1);
            usuarioLojistaRepository.save(usuarioLojista1);

        }


    }

    @Transactional
    public ResponseEntity<Transaction> todasTrasaçoes(Long id) {
        return transactionRepository.findById(id).map(ResponseEntity::ok).orElseThrow(() -> new TransactionException("sem transações esse id"));
    }

    @Transactional
    public List<Transaction> getAll() {
        return transactionRepository.findBytransactions();
    }

    @Transactional
    public Transaction fazerTransaçao(Transaction transaction, Long lojistaId, Long usuarioId) throws MessagingException {

        Optional<UsuarioComum> optionalUsuarioComum = usuarioComumRepository.findById(usuarioId);
        UsuarioComum usuarioComum = optionalUsuarioComum.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
         usuarioComum.setId(usuarioId);
        Optional<UsuarioLojista> OPtionalusuarioLojista = usuarioLojistaRepository.findById(lojistaId);
        UsuarioLojista usuarioLojista = OPtionalusuarioLojista.orElseThrow(() -> new TransactionException("lojista nao encontrado"));
        usuarioLojista.setId(lojistaId);
        calcularSubs(transaction.getValor(), lojistaId, usuarioId);
        if (transaction.equals(transaction.getStatus().equals(StatusTransaction.FAILED))) {
            throw new TransactionException("Error na trasaçao faliu");
        }
        if (transaction.getValor().signum() < 0) {
            throw new TransactionException("o valor tem que ser maior que zero");
        }
        transaction.setDataTransaction(OffsetDateTime.now());
        transaction.setSenderID(usuarioComum);
        transaction.setReceivedID(usuarioLojista);
        emailService.sendEmail("rrenato087@gmail.com", "Detalhes da transferencia", emailService.populations(transaction));
        return transactionRepository.save(transaction);
    }
}
