-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "bank" se ela ainda não existir
CREATE TABLE IF NOT EXISTS bank
(
    -- Identificador único do banco (obrigatório)
    bank_id UUID DEFAULT uuid_generate_v4(),

    -- Código do banco (obrigatório)
    bank_code VARCHAR(20) NOT NULL, 

    -- Nome do banco (obrigatório)
    bank_name VARCHAR(100) NOT NULL, 

    -- Identificador do arquivo associado
    file_id UUID,

    -- Data e hora de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, 

    -- Data e hora da última modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, 

    -- Indica se o banco está ativo (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true, 

    -- Chave primária da tabela 'bank'
    CONSTRAINT bank_pkey PRIMARY KEY (bank_id),

    -- Restrições de unicidade para o código e nome do banco
    CONSTRAINT bank_unique UNIQUE (bank_code, bank_name),

    -- Chave estrangeira para a tabela file
    CONSTRAINT bank_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre bancos
COMMENT ON TABLE bank IS 'Tabela que armazena informações sobre bancos.';

-- Comentários para cada campo
COMMENT ON COLUMN bank.bank_id IS 'Identificador único do banco.';
COMMENT ON COLUMN bank.bank_code IS 'Código do banco, por exemplo, "001" para Banco do Brasil.';
COMMENT ON COLUMN bank.bank_name IS 'Nome do banco, por exemplo, "Banco do Brasil".';
COMMENT ON COLUMN bank.file_id IS 'Identificador do arquivo associado.';
COMMENT ON COLUMN bank.created_date IS 'Data e hora de criação do registro.';
COMMENT ON COLUMN bank.modified_date IS 'Data e hora da última modificação do registro.';
COMMENT ON COLUMN bank.enabled IS 'Indica se o banco está ativo.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT bank_pkey ON bank IS 'Chave primária da tabela bank, identificador único do banco.';
COMMENT ON CONSTRAINT bank_unique ON bank IS 'Restrições de unicidade para o código e nome do banco.';
COMMENT ON CONSTRAINT bank_to_file_fk ON bank IS 'Chave estrangeira referenciando a tabela file para o arquivo associado.';