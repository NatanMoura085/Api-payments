package com.api_payments.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transaction {
    private Long id;
    private Long senderID;
    private Long receivedID;
    private BigDecimal valor;
    private OffsetDateTime dataTrasaction;
    private StatusTransaction status;
}
