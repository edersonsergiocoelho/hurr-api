-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "vehicle_color" se ela ainda não existir
CREATE TABLE IF NOT EXISTS vehicle_color
(
    -- Identificador único da cor do veículo
    vehicle_color_id UUID DEFAULT uuid_generate_v4(),

    -- Nome da cor do veículo (obrigatório)
    vehicle_color_name CHARACTER VARYING(100) NOT NULL,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se a cor está habilitada
    enabled boolean NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'vehicle_color'
    CONSTRAINT vehicle_color_pkey PRIMARY KEY (vehicle_color_id),

    -- Define a restrição de unicidade para o nome da cor do veículo
    CONSTRAINT vehicle_color_unique UNIQUE (vehicle_color_name)
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre cores de veículos
COMMENT ON TABLE vehicle_color IS 'Tabela que armazena informações sobre cores de veículos.';

-- Comentários para cada campo
COMMENT ON COLUMN vehicle_color.vehicle_color_id IS 'Identificador único da cor do veículo.';
COMMENT ON COLUMN vehicle_color.vehicle_color_name IS 'Nome da cor do veículo.';
COMMENT ON COLUMN vehicle_color.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN vehicle_color.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN vehicle_color.enabled IS 'Indica se a cor está habilitada.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT vehicle_color_pkey ON "vehicle_color" IS 'Chave primária da tabela vehicle_color, identificador único da cor do veículo.';
COMMENT ON CONSTRAINT vehicle_color_unique ON "vehicle_color" IS 'Restrição de unicidade para o nome da cor do veículo.';