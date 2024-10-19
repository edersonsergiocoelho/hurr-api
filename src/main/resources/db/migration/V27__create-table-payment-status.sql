-- Cria a tabela "payment_status" se ela ainda não existir
CREATE TABLE IF NOT EXISTS payment_status
(
    -- Identificador único do status do pagamento
    payment_status_id UUID DEFAULT uuid_generate_v4(),

    -- Nome do status do pagamento (obrigatório)
    payment_status_name CHARACTER VARYING(100) NOT NULL,

    -- Identificador do arquivo associado ao status do pagamento
    file_id UUID,

    -- Data de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o status do pagamento está habilitado (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'payment_status'
    CONSTRAINT payment_status_pkey PRIMARY KEY (payment_status_id),

    -- Define a restrição de unicidade para o nome do status do pagamento
    CONSTRAINT payment_status_unique UNIQUE (payment_status_name),

    -- Define a chave estrangeira para o arquivo associado
    CONSTRAINT payment_status_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre status de pagamento
COMMENT ON TABLE payment_status IS 'Tabela que armazena informações sobre status de pagamento.';

-- Comentários para cada campo
COMMENT ON COLUMN payment_status.payment_status_id IS 'Identificador único do status do pagamento.';
COMMENT ON COLUMN payment_status.payment_status_name IS 'Nome do status do pagamento.';
COMMENT ON COLUMN payment_status.file_id IS 'Identificador do arquivo associado ao status do pagamento.';
COMMENT ON COLUMN payment_status.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN payment_status.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN payment_status.enabled IS 'Indica se o status do pagamento está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT payment_status_pkey ON payment_status IS 'Chave primária da tabela payment_status, identificador único do status do pagamento.';
COMMENT ON CONSTRAINT payment_status_unique ON payment_status IS 'Restrição de unicidade para o nome do status do pagamento.';
COMMENT ON CONSTRAINT payment_status_to_file_fk ON payment_status IS 'Chave estrangeira referenciando a tabela file para o arquivo associado ao status do pagamento.';