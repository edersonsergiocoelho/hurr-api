-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "customer_address" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer_address
(
    -- Identificador único do relacionamento entre cliente e endereço
    customer_address_id UUID DEFAULT uuid_generate_v4(),

    -- Identificador do cliente (obrigatório)
    customer_id UUID NOT NULL,

    -- Identificador do endereço (obrigatório)
    address_id UUID NOT NULL,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o relacionamento está habilitado
    enabled boolean NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'customer_address'
    CONSTRAINT customer_address_pkey PRIMARY KEY (customer_address_id),

    -- Define a restrição de unicidade para o relacionamento cliente-endereço
    CONSTRAINT customer_address_unique UNIQUE (customer_id, address_id),

    -- Chave estrangeira que referencia a tabela address
    CONSTRAINT customer_address_to_address_fk FOREIGN KEY (address_id)
        REFERENCES address (address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Chave estrangeira que referencia a tabela customer
    CONSTRAINT customer_address_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar o relacionamento entre clientes e endereços
COMMENT ON TABLE customer_address IS 'Tabela que armazena o relacionamento entre clientes e endereços.';

-- Comentários para cada campo
COMMENT ON COLUMN customer_address.customer_address_id IS 'Identificador único do relacionamento entre cliente e endereço.';
COMMENT ON COLUMN customer_address.customer_id IS 'Identificador do cliente.';
COMMENT ON COLUMN customer_address.address_id IS 'Identificador do endereço.';
COMMENT ON COLUMN customer_address.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN customer_address.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN customer_address.enabled IS 'Indica se o relacionamento está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT customer_address_pkey ON "customer_address" IS 'Chave primária da tabela customer_address, identificador único do relacionamento.';
COMMENT ON CONSTRAINT customer_address_unique ON "customer_address" IS 'Restrição de unicidade para o relacionamento entre cliente e endereço.';
COMMENT ON CONSTRAINT customer_address_to_address_fk ON "customer_address" IS 'Chave estrangeira que referencia a tabela address.';
COMMENT ON CONSTRAINT customer_address_to_customer_fk ON "customer_address" IS 'Chave estrangeira que referencia a tabela customer.';