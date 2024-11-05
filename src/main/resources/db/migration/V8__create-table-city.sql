-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "city" se ela ainda não existir
CREATE TABLE IF NOT EXISTS city
(
    -- Identificador único da cidade
    city_id UUID DEFAULT uuid_generate_v4(),
    
    -- Nome da cidade (obrigatório)
    city_name CHARACTER VARYING(100) NOT NULL,
    
    -- Identificador do estado ao qual a cidade pertence (obrigatório)
    state_id UUID NOT NULL,
    
    -- Indica se o serviço está disponível para a cidade
    service_available BOOLEAN NOT NULL DEFAULT false,
    
    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    
    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    
    -- Indica se a cidade está habilitada
    enabled BOOLEAN NOT NULL DEFAULT true,
    
    -- Define a chave primária para a tabela 'city' baseada no campo 'city_id'
    CONSTRAINT city_pkey PRIMARY KEY (city_id),

    -- Define a restrição de unicidade para o nome da cidade e seu estado
    CONSTRAINT city_unique UNIQUE (state_id, city_name),

    -- Define a chave estrangeira que referencia o estado ao qual a cidade pertence
    CONSTRAINT city_to_state_fk FOREIGN KEY (state_id)
        REFERENCES state (state_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre cidades
COMMENT ON TABLE city IS 'Tabela para armazenar informações sobre cidades, incluindo nome, estado associado e status de habilitação.';

-- Comentários para cada campo
COMMENT ON COLUMN city.city_id IS 'Identificador único da cidade.';
COMMENT ON COLUMN city.city_name IS 'Nome da cidade (obrigatório).';
COMMENT ON COLUMN city.state_id IS 'Identificador do estado ao qual a cidade pertence (obrigatório).';
COMMENT ON COLUMN city.service_available IS 'Indica se o serviço está disponível para a cidade.';
COMMENT ON COLUMN city.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN city.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN city.enabled IS 'Indica se a cidade está habilitada.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT city_pkey ON "city" IS 'Chave primária da tabela city, baseada no campo city_id.';
COMMENT ON CONSTRAINT city_unique ON "city" IS 'Restrição de unicidade para o nome da cidade e seu estado.';
COMMENT ON CONSTRAINT city_to_state_fk ON "city" IS 'Chave estrangeira que referencia o estado ao qual a cidade pertence.';