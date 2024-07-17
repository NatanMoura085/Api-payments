package com.api_payments.api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
@Getter
@Setter
public class UsuarioComumDTO {
    private Long id;
    @NotNull
    private String nomeCompleto;
    @CPF
    private String cpf;
    private String email;
    private String senha;
    @Column(name = "saldo_da_conta")
    private BigDecimal saldoConta;
}
