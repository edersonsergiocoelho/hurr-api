-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "vehicle_fuel_type" se ela ainda não existir
CREATE TABLE IF NOT EXISTS vehicle_fuel_type
(
    -- Identificador único do tipo de combustível do veículo
    vehicle_fuel_type_id UUID DEFAULT uuid_generate_v4(),

    -- Nome do tipo de combustível do veículo (obrigatório e único)
    vehicle_fuel_type_name CHARACTER VARYING(40) NOT NULL UNIQUE,

    -- Identificador do arquivo associado ao tipo de combustível
    file_id UUID,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o tipo de combustível está habilitado
    enabled boolean NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'vehicle_fuel_type'
    CONSTRAINT vehicle_fuel_type_pkey PRIMARY KEY (vehicle_fuel_type_id),

    -- Define a restrição de unicidade para o nome do tipo de combustível
    CONSTRAINT vehicle_fuel_type_unique UNIQUE (vehicle_fuel_type_name),

    -- Define a chave estrangeira para o arquivo associado
    CONSTRAINT vehicle_fuel_type_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre tipos de combustível de veículos
COMMENT ON TABLE vehicle_fuel_type IS 'Tabela que armazena informações sobre tipos de combustível de veículos.';

-- Comentários para cada campo
COMMENT ON COLUMN vehicle_fuel_type.vehicle_fuel_type_id IS 'Identificador único do tipo de combustível do veículo.';
COMMENT ON COLUMN vehicle_fuel_type.vehicle_fuel_type_name IS 'Nome do tipo de combustível do veículo.';
COMMENT ON COLUMN vehicle_fuel_type.file_id IS 'Identificador do arquivo associado ao tipo de combustível.';
COMMENT ON COLUMN vehicle_fuel_type.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN vehicle_fuel_type.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN vehicle_fuel_type.enabled IS 'Indica se o tipo de combustível está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT vehicle_fuel_type_pkey ON "vehicle_fuel_type" IS 'Chave primária da tabela vehicle_fuel_type, identificador único do tipo de combustível.';
COMMENT ON CONSTRAINT vehicle_fuel_type_unique ON "vehicle_fuel_type" IS 'Restrição de unicidade para o nome do tipo de combustível.';
COMMENT ON CONSTRAINT vehicle_fuel_type_to_file_fk ON "vehicle_fuel_type" IS 'Chave estrangeira referenciando a tabela file para o arquivo associado ao tipo de combustível.';