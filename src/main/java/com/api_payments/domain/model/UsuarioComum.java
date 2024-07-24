package com.api_payments.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "usuario_comum")
public class UsuarioComum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String nomeCompleto;
    @CPF
    private String cpf;
    private String email;
    private String senha;
    @Column(name = "saldo_da_conta",precision = 38,scale = 2)
    private BigDecimal saldoConta;
    @Transient

    private UsuarioLojista usuarioLojista;

}
