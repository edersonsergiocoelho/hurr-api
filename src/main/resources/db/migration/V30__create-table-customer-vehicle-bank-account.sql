-- Cria a tabela "customer_vehicle_bank_account" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer_vehicle_bank_account
(
    -- Identificador único da conta bancária do cliente (obrigatório)
    customer_vehicle_bank_account_id UUID DEFAULT uuid_generate_v4(),

    -- Identificador do cliente (obrigatório)
    customer_id UUID NOT NULL,

    -- Identificador do banco (obrigatório)
    bank_id UUID NOT NULL,

    -- Tipo de Pix associado à conta bancária (obrigatório)
    pix_type VARCHAR(20) NOT NULL,

    -- Chave Pix associada à conta bancária (obrigatório)
    pix_key VARCHAR(100) NOT NULL,

    -- Data e hora de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data e hora da última modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se a conta bancária está ativa (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true,

    -- Chave primária da tabela 'customer_vehicle_bank_account'
    CONSTRAINT customer_vehicle_bank_account_pkey PRIMARY KEY (customer_vehicle_bank_account_id),

    -- Chave estrangeira para a tabela customer
    CONSTRAINT customer_vehicle_bank_account_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Chave estrangeira para a tabela bank
    CONSTRAINT customer_vehicle_bank_account_to_bank_fk FOREIGN KEY (bank_id)
        REFERENCES bank (bank_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre contas bancárias dos clientes de veículos
COMMENT ON TABLE customer_vehicle_bank_account IS 'Tabela que armazena informações sobre contas bancárias dos clientes de veículos.';

-- Comentários para cada campo
COMMENT ON COLUMN customer_vehicle_bank_account.customer_vehicle_bank_account_id IS 'Identificador único da conta bancária do cliente.';
COMMENT ON COLUMN customer_vehicle_bank_account.customer_id IS 'Identificador do cliente.';
COMMENT ON COLUMN customer_vehicle_bank_account.bank_id IS 'Identificador do banco.';
COMMENT ON COLUMN customer_vehicle_bank_account.pix_type IS 'Tipo de Pix associado à conta bancária.';
COMMENT ON COLUMN customer_vehicle_bank_account.pix_key IS 'Chave Pix associada à conta bancária.';
COMMENT ON COLUMN customer_vehicle_bank_account.created_date IS 'Data e hora de criação do registro.';
COMMENT ON COLUMN customer_vehicle_bank_account.modified_date IS 'Data e hora da última modificação do registro.';
COMMENT ON COLUMN customer_vehicle_bank_account.enabled IS 'Indica se a conta bancária está ativa.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT customer_vehicle_bank_account_pkey ON customer_vehicle_bank_account IS 'Chave primária da tabela customer_vehicle_bank_account, identificador único da conta bancária do cliente.';
COMMENT ON CONSTRAINT customer_vehicle_bank_account_to_customer_fk ON customer_vehicle_bank_account IS 'Chave estrangeira referenciando a tabela customer para o cliente associado.';
COMMENT ON CONSTRAINT customer_vehicle_bank_account_to_bank_fk ON customer_vehicle_bank_account IS 'Chave estrangeira referenciando a tabela bank para o banco associado.';