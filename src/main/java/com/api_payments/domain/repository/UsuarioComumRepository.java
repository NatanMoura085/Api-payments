package com.api_payments.domain.repository;

import com.api_payments.domain.model.UsuarioComum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioComumRepository extends JpaRepository<UsuarioComum,Long> {
    Optional<UsuarioComum> findBycpf(String cpf);
    Optional<UsuarioComum> findByemail(String email);

}
