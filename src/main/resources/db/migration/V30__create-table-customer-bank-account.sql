CREATE TABLE IF NOT EXISTS customer_bank_account
(
    customer_bank_account_id UUID DEFAULT uuid_generate_v4() PRIMARY KEY, -- Identificador único da conta bancária do cliente
    customer_id UUID NOT NULL, -- Identificador do cliente
    bank_id UUID NOT NULL, -- Identificador do banco
    account_number VARCHAR(20) NOT NULL, -- Número da conta bancária
    account_type VARCHAR(20) NOT NULL, -- Tipo da conta bancária, por exemplo, "corrente" ou "poupança"
    branch_number VARCHAR(20) NOT NULL, -- Número da agência bancária
    pix_key VARCHAR(100), -- Chave Pix associada à conta bancária
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data e hora de criação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data e hora da última modificação do registro
    enabled BOOLEAN NOT NULL DEFAULT true, -- Indica se a conta bancária está ativa
    CONSTRAINT customer_bank_account_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_bank_account_to_bank_fk FOREIGN KEY (bank_id)
        REFERENCES bank (bank_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE customer_bank_account IS 'Tabela que armazena informações sobre as contas bancárias dos clientes.';
COMMENT ON COLUMN customer_bank_account.customer_bank_account_id IS 'Identificador único da conta bancária do cliente';
COMMENT ON COLUMN customer_bank_account.customer_id IS 'Identificador do cliente';
COMMENT ON COLUMN customer_bank_account.bank_id IS 'Identificador do banco';
COMMENT ON COLUMN customer_bank_account.account_number IS 'Número da conta bancária';
COMMENT ON COLUMN customer_bank_account.account_type IS 'Tipo da conta bancária, por exemplo, "corrente" ou "poupança"';
COMMENT ON COLUMN customer_bank_account.branch_number IS 'Número da agência bancária';
COMMENT ON COLUMN customer_bank_account.pix_key IS 'Chave Pix associada à conta bancária';
COMMENT ON COLUMN customer_bank_account.created_date IS 'Data e hora de criação do registro';
COMMENT ON COLUMN customer_bank_account.modified_date IS 'Data e hora da última modificação do registro';
COMMENT ON COLUMN customer_bank_account.enabled IS 'Indica se a conta bancária está ativa';