package com.api_payments.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
@Entity
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "usuario_lojista")
public class UsuarioLojista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String nomeCompleto;
    @Max(11)
    private String cpf;
    private String email;
    @NotNull
    private String senha;
    @Column(name = "saldo_conta")
    private BigDecimal saldoConta;

}
