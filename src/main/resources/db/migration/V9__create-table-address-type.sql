-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "address_type" se ela ainda não existir
CREATE TABLE IF NOT EXISTS address_type
(
    -- Identificador único do tipo de endereço
    address_type_id UUID DEFAULT uuid_generate_v4(),
    
    -- Nome do tipo de endereço (obrigatório)
    address_type_name CHARACTER VARYING(100) NOT NULL,
    
    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    
    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    
    -- Indica se o tipo de endereço está habilitado
    enabled BOOLEAN NOT NULL DEFAULT true,
    
    -- Define a chave primária para a tabela 'address_type' baseada no campo 'address_type_id'
    CONSTRAINT address_type_pkey PRIMARY KEY (address_type_id),

    -- Define a restrição de unicidade para o nome do tipo de endereço
    CONSTRAINT address_type_unique UNIQUE (address_type_name)
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre tipos de endereço
COMMENT ON TABLE address_type IS 'Tabela para armazenar informações sobre tipos de endereço, incluindo nome, datas de criação/modificação e status de habilitação.';

-- Comentários para cada campo
COMMENT ON COLUMN address_type.address_type_id IS 'Identificador único do tipo de endereço.';
COMMENT ON COLUMN address_type.address_type_name IS 'Nome do tipo de endereço (obrigatório).';
COMMENT ON COLUMN address_type.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN address_type.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN address_type.enabled IS 'Indica se o tipo de endereço está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT address_type_pkey ON "address_type" IS 'Chave primária da tabela address_type, baseada no campo address_type_id.';
COMMENT ON CONSTRAINT address_type_unique ON "address_type" IS 'Restrição de unicidade para o nome do tipo de endereço.';