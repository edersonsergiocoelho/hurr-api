-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "vehicle_brand" se ela ainda não existir
CREATE TABLE IF NOT EXISTS vehicle_brand
(
    -- Identificador único da marca do veículo
    vehicle_brand_id UUID DEFAULT uuid_generate_v4(),

    -- Nome da marca do veículo (obrigatório e único)
    vehicle_brand_name CHARACTER VARYING(100) NOT NULL,

    -- Identificador do arquivo relacionado à marca do veículo
    file_id UUID,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se a marca do veículo está habilitada
    enabled boolean NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'vehicle_brand'
    CONSTRAINT vehicle_brand_pkey PRIMARY KEY (vehicle_brand_id),

    -- Define a restrição de unicidade para o nome da marca do veículo
    CONSTRAINT vehicle_brand_unique UNIQUE (vehicle_brand_name),

    -- Chave estrangeira que referencia a tabela file
    CONSTRAINT vehicle_brand_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre marcas de veículos
COMMENT ON TABLE vehicle_brand IS 'Tabela que armazena informações sobre marcas de veículos.';

-- Comentários para cada campo
COMMENT ON COLUMN vehicle_brand.vehicle_brand_id IS 'Identificador único da marca do veículo.';
COMMENT ON COLUMN vehicle_brand.vehicle_brand_name IS 'Nome da marca do veículo.';
COMMENT ON COLUMN vehicle_brand.file_id IS 'Identificador do arquivo relacionado à marca do veículo.';
COMMENT ON COLUMN vehicle_brand.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN vehicle_brand.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN vehicle_brand.enabled IS 'Indica se a marca do veículo está habilitada.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT vehicle_brand_pkey ON "vehicle_brand" IS 'Chave primária da tabela vehicle_brand, identificador único da marca.';
COMMENT ON CONSTRAINT vehicle_brand_unique ON "vehicle_brand" IS 'Restrição de unicidade para o nome da marca do veículo.';
COMMENT ON CONSTRAINT vehicle_brand_to_file_fk ON "vehicle_brand" IS 'Chave estrangeira que referencia a tabela file.';