CREATE TABLE IF NOT EXISTS country
(
    country_id UUID DEFAULT uuid_generate_v4(), -- Identificador único do país
    country_name CHARACTER VARYING(100) NOT NULL UNIQUE, -- Nome do país (obrigatório e único)
    country_code CHAR(5) NOT NULL UNIQUE, -- Código do país (obrigatório e único)
    service_available boolean NOT NULL DEFAULT false, -- Indica se o serviço está disponível para o país
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data de criação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data de modificação do registro
    enabled boolean NOT NULL DEFAULT true, -- Indica se o país está habilitado
    CONSTRAINT country_pkey PRIMARY KEY (country_id) -- Chave primária da tabela (identificador único do país)
);

COMMENT ON TABLE country IS 'Tabela para armazenar informações sobre países.';

COMMENT ON COLUMN country.country_id IS 'Identificador único do país.';
COMMENT ON COLUMN country.country_name IS 'Nome do país.';
COMMENT ON COLUMN country.country_code IS 'Código abreviado do país.';
COMMENT ON COLUMN country.service_available IS 'Indica se o serviço está disponível no país.';
COMMENT ON COLUMN country.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN country.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN country.enabled IS 'Indicador de ativação do registro.';