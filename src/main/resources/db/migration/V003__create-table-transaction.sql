
CREATE TABLE IF NOT EXISTS transação (
    id BIGSERIAL PRIMARY KEY,
    senderID BIGINT NOT NULL,
    receivedID BIGINT NOT NULL,
    valor DECIMAL(19, 2) NOT NULL,
    data_transaction TIMESTAMP WITH TIME ZONE NOT NULL,
    status VARCHAR(50) NOT NULL
);

ALTER TABLE transação
ADD CONSTRAINT fk_transação_usuario
FOREIGN KEY (senderID) REFERENCES usuario_comum(id);

ALTER TABLE transação
ADD CONSTRAINT fk_transação_lojista
FOREIGN KEY (receivedID) REFERENCES usuario_lojista(id);