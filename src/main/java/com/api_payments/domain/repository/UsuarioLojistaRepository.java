package com.api_payments.domain.repository;

import com.api_payments.domain.model.UsuarioLojista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioLojistaRepository extends JpaRepository<UsuarioLojista,Long> {
}
