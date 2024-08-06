CREATE TABLE IF NOT EXISTS tb_login (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    login VARCHAR(255) UNIQUE,
    senha VARCHAR(255),
    role VARCHAR(50)
);