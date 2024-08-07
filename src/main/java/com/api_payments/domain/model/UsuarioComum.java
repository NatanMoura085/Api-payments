package com.api_payments.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "login_id",referencedColumnName = "id")
    private Login login;
    @JsonManagedReference
    @OneToMany(mappedBy = "senderID",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Transaction> transactions= new ArrayList<>();
}
