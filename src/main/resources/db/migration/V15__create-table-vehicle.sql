-- Cria a tabela "vehicle" se ela ainda não existir
CREATE TABLE IF NOT EXISTS vehicle
(
    -- Identificador único do veículo
    vehicle_id UUID DEFAULT uuid_generate_v4(),

    -- Nome do veículo (obrigatório)
    vehicle_name CHARACTER VARYING(100) NOT NULL,

    -- Identificador da marca do veículo (chave estrangeira)
    vehicle_brand_id UUID NOT NULL,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o veículo está habilitado
    enabled boolean NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'vehicle'
    CONSTRAINT vehicle_pkey PRIMARY KEY (vehicle_id),

    -- Define a restrição de unicidade para a combinação de marca e nome do veículo
    CONSTRAINT vehicle_unique UNIQUE (vehicle_brand_id, vehicle_name),

    -- Chave estrangeira que referencia a tabela vehicle_brand
    CONSTRAINT vehicle_to_brand_fk FOREIGN KEY (vehicle_brand_id)
        REFERENCES vehicle_brand (vehicle_brand_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre veículos
COMMENT ON TABLE vehicle IS 'Tabela que armazena informações sobre veículos.';

-- Comentários para cada campo
COMMENT ON COLUMN vehicle.vehicle_id IS 'Identificador único do veículo.';
COMMENT ON COLUMN vehicle.vehicle_name IS 'Nome do veículo.';
COMMENT ON COLUMN vehicle.vehicle_brand_id IS 'Identificador da marca do veículo (chave estrangeira).';
COMMENT ON COLUMN vehicle.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN vehicle.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN vehicle.enabled IS 'Indica se o veículo está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT vehicle_pkey ON "vehicle" IS 'Chave primária da tabela vehicle, identificador único do veículo.';
COMMENT ON CONSTRAINT vehicle_unique ON "vehicle" IS 'Restrição de unicidade para a combinação de marca e nome do veículo.';
COMMENT ON CONSTRAINT vehicle_to_brand_fk ON "vehicle" IS 'Chave estrangeira que referencia a tabela vehicle_brand.';