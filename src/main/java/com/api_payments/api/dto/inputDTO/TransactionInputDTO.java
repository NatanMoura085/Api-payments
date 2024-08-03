package com.api_payments.api.dto.inputDTO;

import com.api_payments.domain.model.StatusTransaction;
import com.api_payments.domain.model.UsuarioComum;
import com.api_payments.domain.model.UsuarioLojista;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Getter
@Setter
@NoArgsConstructor
public class TransactionInputDTO {
    private Long id;
   // private UsuarioComum senderID;
    //private UsuarioLojista receivedID;

    private BigDecimal valor;
    private OffsetDateTime dataTransaction;

    @Enumerated(EnumType.STRING)
    private StatusTransaction status;

//    public TransactionInputDTO(UsuarioComum usuarioComum, UsuarioLojista usuarioLojista) {
//        this.senderID = usuarioComum;
//        this.receivedID = usuarioLojista;
//    }

}
