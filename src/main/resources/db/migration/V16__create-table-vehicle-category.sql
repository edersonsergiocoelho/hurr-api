-- Cria a tabela "vehicle_category" se ela ainda não existir
CREATE TABLE IF NOT EXISTS vehicle_category
(
    -- Identificador único da categoria do veículo
    vehicle_category_id UUID DEFAULT uuid_generate_v4(),

    -- Nome da categoria do veículo (obrigatório)
    vehicle_category_name CHARACTER VARYING(100) NOT NULL,

    -- Identificador de arquivo associado (opcional)
    file_id UUID,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se a categoria está habilitada
    enabled boolean NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'vehicle_category'
    CONSTRAINT vehicle_category_pkey PRIMARY KEY (vehicle_category_id),

    -- Define a restrição de unicidade para o nome da categoria do veículo
    CONSTRAINT vehicle_category_unique UNIQUE (vehicle_category_name),

    -- Chave estrangeira que referencia a tabela file
    CONSTRAINT vehicle_category_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre categorias de veículos
COMMENT ON TABLE vehicle_category IS 'Tabela que armazena informações sobre categorias de veículos.';

-- Comentários para cada campo
COMMENT ON COLUMN vehicle_category.vehicle_category_id IS 'Identificador único da categoria do veículo.';
COMMENT ON COLUMN vehicle_category.vehicle_category_name IS 'Nome da categoria do veículo.';
COMMENT ON COLUMN vehicle_category.file_id IS 'Identificador de arquivo associado (opcional).';
COMMENT ON COLUMN vehicle_category.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN vehicle_category.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN vehicle_category.enabled IS 'Indica se a categoria está habilitada.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT vehicle_category_pkey ON "vehicle_category" IS 'Chave primária da tabela vehicle_category, identificador único da categoria do veículo.';
COMMENT ON CONSTRAINT vehicle_category_unique ON "vehicle_category" IS 'Restrição de unicidade para o nome da categoria do veículo.';
COMMENT ON CONSTRAINT vehicle_category_to_file_fk ON "vehicle_category" IS 'Chave estrangeira que referencia a tabela file.';