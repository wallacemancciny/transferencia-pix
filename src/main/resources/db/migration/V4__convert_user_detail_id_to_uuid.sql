-- 1. Habilita extensão de UUID (se não existir)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- 2. Remove o DEFAULT da coluna id (ela tem nextval() por causa do BIGSERIAL)
ALTER TABLE user_detail
    ALTER COLUMN id DROP DEFAULT;

-- 3. Remove a sequence criada automaticamente pelo BIGSERIAL (opcional, mas recomendado)
DROP SEQUENCE IF EXISTS user_detail_id_seq;

-- 4. Converte a coluna id para UUID gerando novos UUIDs
ALTER TABLE user_detail
    ALTER COLUMN id TYPE UUID USING gen_random_uuid();
