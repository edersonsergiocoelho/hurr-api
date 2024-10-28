-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "payment_method" se ela ainda não existir
CREATE TABLE IF NOT EXISTS payment_method
(
    -- Identificador único do método de pagamento
    payment_method_id UUID DEFAULT uuid_generate_v4(),

    -- Nome do método de pagamento (obrigatório)
    payment_method_name CHARACTER VARYING(100) NOT NULL,

    -- Identificador do arquivo associado ao método de pagamento
    file_id UUID,

    -- Data de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o método de pagamento está habilitado (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'payment_method'
    CONSTRAINT payment_method_pkey PRIMARY KEY (payment_method_id),

    -- Define a restrição de unicidade para o nome do método de pagamento
    CONSTRAINT payment_method_unique UNIQUE (payment_method_name),

    -- Define a chave estrangeira para o arquivo associado
    CONSTRAINT payment_method_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre métodos de pagamento
COMMENT ON TABLE payment_method IS 'Tabela que armazena informações sobre métodos de pagamento.';

-- Comentários para cada campo
COMMENT ON COLUMN payment_method.payment_method_id IS 'Identificador único do método de pagamento.';
COMMENT ON COLUMN payment_method.payment_method_name IS 'Nome do método de pagamento.';
COMMENT ON COLUMN payment_method.file_id IS 'Identificador do arquivo associado ao método de pagamento.';
COMMENT ON COLUMN payment_method.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN payment_method.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN payment_method.enabled IS 'Indica se o método de pagamento está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT payment_method_pkey ON payment_method IS 'Chave primária da tabela payment_method, identificador único do método de pagamento.';
COMMENT ON CONSTRAINT payment_method_unique ON payment_method IS 'Restrição de unicidade para o nome do método de pagamento.';
COMMENT ON CONSTRAINT payment_method_to_file_fk ON payment_method IS 'Chave estrangeira referenciando a tabela file para o arquivo associado ao método de pagamento.';