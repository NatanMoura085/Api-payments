package com.api_payments.domain.repository;

import com.api_payments.domain.model.UsuarioComum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioComumRepository extends JpaRepository<UsuarioComum,Long> {

}
