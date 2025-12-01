CREATE TABLE user_detail (
        id BIGSERIAL PRIMARY KEY,
        user_id TEXT NOT NULL UNIQUE,
        email VARCHAR(255) NOT NULL UNIQUE,
        telefone VARCHAR(15) NOT NULL,
        endereco VARCHAR(255) NOT NULL,
        cep VARCHAR(10) NOT NULL,
        numero_residencia INTEGER,
        tipo VARCHAR(50),

        CONSTRAINT fk_user
            FOREIGN KEY(user_id)
            REFERENCES users(id)
            ON DELETE CASCADE
);
