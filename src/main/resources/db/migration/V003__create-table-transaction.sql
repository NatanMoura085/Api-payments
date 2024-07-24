CREATE TABLE transação (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    lojista_id BIGINT NOT NULL,
    valor DECIMAL(19, 2) NOT NULL,
    data_transaction TIMESTAMP WITH TIME ZONE NOT NULL,
    status VARCHAR(50) NOT NULL
);

ALTER TABLE transação
ADD CONSTRAINT fk_transação_usuario
FOREIGN KEY (usuario_id) REFERENCES usuario_comum(id);

ALTER TABLE transação
ADD CONSTRAINT fk_transação_lojista
FOREIGN KEY (lojista_id) REFERENCES usuario_lojista(id);