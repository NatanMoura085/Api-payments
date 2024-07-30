package com.api_payments.domain.repository;

import com.api_payments.domain.model.UsuarioLojista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface UsuarioLojistaRepository extends JpaRepository<UsuarioLojista,Long> {
    Optional findBycpf(String cpf);
    Optional findBysaldoConta(BigDecimal valor);
}
