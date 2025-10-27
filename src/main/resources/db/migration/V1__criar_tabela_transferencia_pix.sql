CREATE TABLE transferencia_pix (
    id BIGSERIAL PRIMARY KEY,
    codigo_transacao VARCHAR(100) NOT NULL UNIQUE,
    chave_origem VARCHAR(255) NOT NULL,
    nome_origem VARCHAR(255) NOT NULL,
    banco_origem VARCHAR(100) NOT NULL,
    chave_destino VARCHAR(255) NOT NULL,
    nome_destino VARCHAR(255) NOT NULL,
    valor NUMERIC(15,2) NOT NULL,
    data_transferencia TIMESTAMP NOT NULL DEFAULT NOW(),
    status VARCHAR(30) NOT NULL,
    mensagem TEXT,
    criado_em TIMESTAMP DEFAULT NOW(),
    atualizado_em TIMESTAMP DEFAULT NOW()
);
