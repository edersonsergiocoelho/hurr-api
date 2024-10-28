-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "address_address_type" se ela ainda não existir
CREATE TABLE IF NOT EXISTS address_address_type
(
    -- Identificador do endereço (obrigatório)
    address_id UUID NOT NULL,

    -- Identificador do tipo de endereço (obrigatório)
    address_type_id UUID NOT NULL,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se a associação entre endereço e tipo de endereço está habilitada
    enabled BOOLEAN NOT NULL DEFAULT true,

    -- Define a chave primária para a tabela 'address_address_type' baseada nos campos 'address_type_id' e 'address_id'
    CONSTRAINT address_address_type_pkey PRIMARY KEY (address_type_id, address_id),

    -- Define a restrição de unicidade para a associação entre endereço e tipo de endereço
    CONSTRAINT address_address_type_unique UNIQUE (address_id, address_type_id),

    -- Define a chave estrangeira para o endereço
    CONSTRAINT address_address_type_to_address_fk FOREIGN KEY (address_id)
        REFERENCES address (address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o tipo de endereço
    CONSTRAINT address_address_type_to_address_type_fk FOREIGN KEY (address_type_id)
        REFERENCES address_type (address_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar a associação entre endereços e tipos de endereço
COMMENT ON TABLE address_address_type IS 'Tabela que relaciona endereços a seus respectivos tipos de endereço.';

-- Comentários para cada campo
COMMENT ON COLUMN address_address_type.address_id IS 'Identificador do endereço (obrigatório).';
COMMENT ON COLUMN address_address_type.address_type_id IS 'Identificador do tipo de endereço (obrigatório).';
COMMENT ON COLUMN address_address_type.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN address_address_type.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN address_address_type.enabled IS 'Indica se a associação entre endereço e tipo de endereço está habilitada.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT address_address_type_pkey ON "address_address_type" IS 'Chave primária da tabela address_address_type, baseada nos campos address_type_id e address_id.';
COMMENT ON CONSTRAINT address_address_type_unique ON "address_address_type" IS 'Restrição de unicidade para a associação entre endereço e tipo de endereço.';
COMMENT ON CONSTRAINT address_address_type_to_address_fk ON "address_address_type" IS 'Chave estrangeira que referencia a tabela address.';
COMMENT ON CONSTRAINT address_address_type_to_address_type_fk ON "address_address_type" IS 'Chave estrangeira que referencia a tabela address_type.';