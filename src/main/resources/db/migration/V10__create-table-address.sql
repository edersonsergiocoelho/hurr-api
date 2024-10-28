-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "address" se ela ainda não existir
CREATE TABLE IF NOT EXISTS address (
    -- Identificador único do endereço
    address_id UUID DEFAULT uuid_generate_v4(),
    
    -- Endereço da rua (obrigatório)
    street_address CHARACTER VARYING(100) NOT NULL,
    
    -- Número do endereço (obrigatório)
    number INT NOT NULL,
    
    -- Complemento do endereço (opcional)
    complement CHARACTER VARYING(100),
    
    -- Identificador do país (obrigatório)
    country_id UUID NOT NULL,
    
    -- Identificador da cidade (obrigatório)
    city_id UUID NOT NULL,
    
    -- Identificador do estado (obrigatório)
    state_id UUID NOT NULL,
    
    -- Código postal (obrigatório)
    zip_code CHARACTER VARYING(20) NOT NULL,
    
    -- Apelido do endereço (obrigatório)
    nickname CHARACTER VARYING(100) NOT NULL,
    
    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    
    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    
    -- Indica se o endereço está habilitado
    enabled BOOLEAN NOT NULL DEFAULT true,
    
    -- Define a chave primária para a tabela 'address' baseada no campo 'address_id'
    CONSTRAINT address_pkey PRIMARY KEY (address_id),

    -- Define a restrição de unicidade para o endereço, garantindo que não haja duplicatas
    CONSTRAINT address_unique UNIQUE (country_id, city_id, state_id, street_address, "number", zip_code),

    -- Define a chave estrangeira para o país
    CONSTRAINT address_to_country_fk FOREIGN KEY (country_id)
        REFERENCES country (country_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para a cidade
    CONSTRAINT address_to_city_fk FOREIGN KEY (city_id)
        REFERENCES city (city_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o estado
    CONSTRAINT address_to_state_fk FOREIGN KEY (state_id)
        REFERENCES state (state_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre endereços
COMMENT ON TABLE address IS 'Tabela para armazenar informações sobre endereços, incluindo detalhes como rua, número, cidade, estado e país.';

-- Comentários para cada campo
COMMENT ON COLUMN address.address_id IS 'Identificador único do endereço.';
COMMENT ON COLUMN address.street_address IS 'Endereço da rua (obrigatório).';
COMMENT ON COLUMN address.number IS 'Número do endereço (obrigatório).';
COMMENT ON COLUMN address.complement IS 'Complemento do endereço (opcional).';
COMMENT ON COLUMN address.country_id IS 'Identificador do país (obrigatório).';
COMMENT ON COLUMN address.city_id IS 'Identificador da cidade (obrigatório).';
COMMENT ON COLUMN address.state_id IS 'Identificador do estado (obrigatório).';
COMMENT ON COLUMN address.zip_code IS 'Código postal (obrigatório).';
COMMENT ON COLUMN address.nickname IS 'Apelido do endereço (obrigatório).';
COMMENT ON COLUMN address.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN address.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN address.enabled IS 'Indica se o endereço está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT address_pkey ON "address" IS 'Chave primária da tabela address, baseada no campo address_id.';
COMMENT ON CONSTRAINT address_unique ON "address" IS 'Restrição de unicidade para o endereço, garantindo que não haja duplicatas.';
COMMENT ON CONSTRAINT address_to_country_fk ON "address" IS 'Chave estrangeira que referencia a tabela country.';
COMMENT ON CONSTRAINT address_to_city_fk ON "address" IS 'Chave estrangeira que referencia a tabela city.';
COMMENT ON CONSTRAINT address_to_state_fk ON "address" IS 'Chave estrangeira que referencia a tabela state.';