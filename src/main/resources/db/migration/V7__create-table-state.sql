-- Cria a tabela "state" se ela ainda não existir
CREATE TABLE IF NOT EXISTS state
(
    -- Identificador único do estado
    state_id UUID DEFAULT uuid_generate_v4(),
    
    -- Nome do estado (obrigatório)
    state_name CHARACTER VARYING(100) NOT NULL,
    
    -- Identificador do país ao qual o estado pertence (obrigatório)
    country_id UUID NOT NULL,
    
    -- Indica se o serviço está disponível para o estado
    service_available BOOLEAN NOT NULL DEFAULT false,
    
    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    
    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    
    -- Indica se o estado está habilitado
    enabled BOOLEAN NOT NULL DEFAULT true,
    
    -- Define a chave primária para a tabela 'state' baseada no campo 'state_id'
    CONSTRAINT state_pkey PRIMARY KEY (state_id),

    -- Define a restrição de unicidade para o nome do estado e seu país
    CONSTRAINT state_unique UNIQUE (country_id, state_name),

    -- Define a chave estrangeira que referencia o país ao qual o estado pertence
    CONSTRAINT state_to_country_fk FOREIGN KEY (country_id)
        REFERENCES country (country_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre estados
COMMENT ON TABLE state IS 'Tabela para armazenar informações sobre estados, incluindo nome, país associado, e status de habilitação.';

-- Comentários para cada campo
COMMENT ON COLUMN state.state_id IS 'Identificador único do estado.';
COMMENT ON COLUMN state.state_name IS 'Nome do estado (obrigatório).';
COMMENT ON COLUMN state.country_id IS 'Identificador do país ao qual o estado pertence (obrigatório).';
COMMENT ON COLUMN state.service_available IS 'Indica se o serviço está disponível para o estado.';
COMMENT ON COLUMN state.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN state.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN state.enabled IS 'Indica se o estado está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT state_pkey ON "state" IS 'Chave primária da tabela state, baseada no campo state_id.';
COMMENT ON CONSTRAINT state_unique ON "state" IS 'Restrição de unicidade para o nome do estado e seu país.';
COMMENT ON CONSTRAINT state_to_country_fk ON "state" IS 'Chave estrangeira que referencia o país ao qual o estado pertence.';