package com.api_payments.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Entity
@Getter
@Setter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @ManyToOne
    @JoinColumn(name = "senderID")
    private UsuarioComum senderID;
    @ManyToOne
    private UsuarioLojista receivedID;
    private BigDecimal valor;
    private OffsetDateTime dataTransaction;
    @Enumerated(EnumType.STRING)
    private StatusTransaction status;
}
