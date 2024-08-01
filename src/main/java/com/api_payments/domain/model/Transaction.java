package com.api_payments.domain.model;

import com.api_payments.domain.model.StatusTransaction;
import com.api_payments.domain.model.UsuarioComum;
import com.api_payments.domain.model.UsuarioLojista;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "senderID", referencedColumnName = "id")
    @JsonBackReference
    private UsuarioComum senderID;

    @ManyToOne
    @JoinColumn(name = "receivedID", referencedColumnName = "id")
    private UsuarioLojista receivedID;

    private BigDecimal valor;
    private OffsetDateTime dataTransaction;

    @Enumerated(EnumType.STRING)
    private StatusTransaction status;

    public Transaction(UsuarioComum usuarioComum, UsuarioLojista usuarioLojista) {
        this.senderID = usuarioComum;
        this.receivedID = usuarioLojista;
    }
}
