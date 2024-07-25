package com.api_payments.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "transação")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario_id")
    private Long senderID;
    @Column(name = "lojista_id")
    private Long receivedID;
    private BigDecimal valor;
    @Column(name = "data_transaction")
    private OffsetDateTime dataTrasaction;
    @Enumerated(EnumType.STRING)
    private StatusTransaction status;
}
