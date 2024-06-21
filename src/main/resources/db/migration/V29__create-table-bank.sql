CREATE TABLE IF NOT EXISTS bank
(
    bank_id UUID DEFAULT uuid_generate_v4() PRIMARY KEY, -- Identificador único do banco
    bank_code VARCHAR(20) NOT NULL, -- Código do banco, por exemplo, "001" para Banco do Brasil
    bank_name VARCHAR(100) NOT NULL, -- Nome do banco, por exemplo, "Banco do Brasil"
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data e hora de criação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data e hora da última modificação do registro
    enabled BOOLEAN NOT NULL DEFAULT true -- Indica se o banco está ativo
);

COMMENT ON TABLE bank IS 'Tabela que armazena informações sobre os bancos.';
COMMENT ON COLUMN bank.bank_id IS 'Identificador único do banco';
COMMENT ON COLUMN bank.bank_code IS 'Código do banco, por exemplo, "001" para Banco do Brasil';
COMMENT ON COLUMN bank.bank_name IS 'Nome do banco, por exemplo, "Banco do Brasil"';
COMMENT ON COLUMN bank.created_date IS 'Data e hora de criação do registro';
COMMENT ON COLUMN bank.modified_date IS 'Data e hora da última modificação do registro';
COMMENT ON COLUMN bank.enabled IS 'Indica se o banco está ativo';