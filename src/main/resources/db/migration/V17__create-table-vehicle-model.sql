-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "vehicle_model" se ela ainda não existir
CREATE TABLE IF NOT EXISTS vehicle_model
(
    -- Identificador único do modelo do veículo
    vehicle_model_id UUID DEFAULT uuid_generate_v4(),

    -- Nome do modelo do veículo (obrigatório)
    vehicle_model_name CHARACTER VARYING(100) NOT NULL,

    -- Identificador do veículo associado (obrigatório)
    vehicle_id UUID NOT NULL,

    -- Identificador da categoria do veículo (obrigatório)
    vehicle_category_id UUID NOT NULL,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o modelo está habilitado
    enabled boolean NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'vehicle_model'
    CONSTRAINT vehicle_model_pkey PRIMARY KEY (vehicle_model_id),

    -- Define a restrição de unicidade para a combinação de veículo, categoria e nome do modelo
    CONSTRAINT vehicle_model_unique UNIQUE (vehicle_id, vehicle_category_id, vehicle_model_name),

    -- Chave estrangeira que referencia a tabela vehicle
    CONSTRAINT vehicle_model_to_vehicle_fk FOREIGN KEY (vehicle_id)
        REFERENCES vehicle (vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Chave estrangeira que referencia a tabela vehicle_category
    CONSTRAINT vehicle_model_to_vehicle_category_fk FOREIGN KEY (vehicle_category_id)
        REFERENCES vehicle_category (vehicle_category_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre modelos de veículos
COMMENT ON TABLE vehicle_model IS 'Tabela que armazena informações sobre modelos de veículos.';

-- Comentários para cada campo
COMMENT ON COLUMN vehicle_model.vehicle_model_id IS 'Identificador único do modelo do veículo.';
COMMENT ON COLUMN vehicle_model.vehicle_model_name IS 'Nome do modelo do veículo.';
COMMENT ON COLUMN vehicle_model.vehicle_id IS 'Identificador do veículo associado.';
COMMENT ON COLUMN vehicle_model.vehicle_category_id IS 'Identificador da categoria do veículo.';
COMMENT ON COLUMN vehicle_model.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN vehicle_model.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN vehicle_model.enabled IS 'Indica se o modelo está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT vehicle_model_pkey ON "vehicle_model" IS 'Chave primária da tabela vehicle_model, identificador único do modelo do veículo.';
COMMENT ON CONSTRAINT vehicle_model_unique ON "vehicle_model" IS 'Restrição de unicidade para a combinação de veículo, categoria e nome do modelo.';
COMMENT ON CONSTRAINT vehicle_model_to_vehicle_fk ON "vehicle_model" IS 'Chave estrangeira que referencia a tabela vehicle.';
COMMENT ON CONSTRAINT vehicle_model_to_vehicle_category_fk ON "vehicle_model" IS 'Chave estrangeira que referencia a tabela vehicle_category.';