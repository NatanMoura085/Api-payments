package com.api_payments.domain.repository;

import com.api_payments.domain.model.Transaction;
import com.api_payments.domain.model.UsuarioLojista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT t FROM Transaction t")
   List<Transaction> findBytransactions();
}
