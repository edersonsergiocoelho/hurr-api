-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "country" se ela ainda não existir
CREATE TABLE IF NOT EXISTS country
(
    -- Identificador único do país
    country_id UUID DEFAULT uuid_generate_v4(),
    
    -- Nome do país (obrigatório e único)
    country_name CHARACTER VARYING(100) NOT NULL,
    
    -- Código do país (obrigatório e único)
    country_code CHAR(5) NOT NULL,
    
    -- Indica se o serviço está disponível para o país
    service_available BOOLEAN NOT NULL DEFAULT false,
    
    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    
    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    
    -- Indica se o país está habilitado
    enabled BOOLEAN NOT NULL DEFAULT true,
    
    -- Define a chave primária para a tabela 'country' baseada no campo 'country_id'
    CONSTRAINT country_pkey PRIMARY KEY (country_id),

    -- Define a restrição de unicidade para o nome e código do país
    CONSTRAINT country_unique UNIQUE (country_name, country_code)
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre países
COMMENT ON TABLE country IS 'Tabela para armazenar informações sobre países.';

-- Comentários para cada campo
COMMENT ON COLUMN country.country_id IS 'Identificador único do país.';
COMMENT ON COLUMN country.country_name IS 'Nome do país (obrigatório e único).';
COMMENT ON COLUMN country.country_code IS 'Código abreviado do país (obrigatório e único).';
COMMENT ON COLUMN country.service_available IS 'Indica se o serviço está disponível no país.';
COMMENT ON COLUMN country.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN country.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN country.enabled IS 'Indica se o país está habilitado.';

-- Comentários para as constraints
COMMENT ON CONSTRAINT country_pkey ON "country" IS 'Chave primária da tabela country, baseada no campo country_id.';
COMMENT ON CONSTRAINT country_unique ON "country" IS 'Restrição de unicidade para o nome e código do país.';