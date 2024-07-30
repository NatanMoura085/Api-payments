package com.api_payments.domain.model;

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
    @JoinColumns({@JoinColumn(name ="senderID",referencedColumnName ="id",insertable = false,updatable = false)
    })
    @JsonBackReference
    private UsuarioComum senderID;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "receivedid_id",referencedColumnName = "id",insertable = false,updatable = false)})
    private UsuarioLojista receivedID;
    private BigDecimal valor;
    private OffsetDateTime dataTransaction;
    @Enumerated(EnumType.STRING)
    private StatusTransaction status;


    public Transaction(UsuarioComum usuarioComum, UsuarioLojista usuarioLojista){
        this.senderID = usuarioComum;
        this.receivedID = usuarioLojista;

    }

}
