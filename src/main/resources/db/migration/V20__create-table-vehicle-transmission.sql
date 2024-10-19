-- Cria a tabela "vehicle_transmission" se ela ainda não existir
CREATE TABLE IF NOT EXISTS vehicle_transmission
(
    -- Identificador único da transmissão do veículo
    vehicle_transmission_id UUID DEFAULT uuid_generate_v4(),

    -- Nome da transmissão do veículo (obrigatório)
    vehicle_transmission_name CHARACTER VARYING(40) NOT NULL,

    -- Identificador do arquivo associado à transmissão
    file_id UUID,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se a transmissão está habilitada
    enabled boolean NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'vehicle_transmission'
    CONSTRAINT vehicle_transmission_pkey PRIMARY KEY (vehicle_transmission_id),

    -- Define a restrição de unicidade para o nome da transmissão
    CONSTRAINT vehicle_transmission_unique UNIQUE (vehicle_transmission_name),

    -- Define a chave estrangeira para o arquivo associado
    CONSTRAINT vehicle_transmission_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre transmissões de veículos
COMMENT ON TABLE vehicle_transmission IS 'Tabela que armazena informações sobre transmissões de veículos.';

-- Comentários para cada campo
COMMENT ON COLUMN vehicle_transmission.vehicle_transmission_id IS 'Identificador único da transmissão do veículo.';
COMMENT ON COLUMN vehicle_transmission.vehicle_transmission_name IS 'Nome da transmissão do veículo.';
COMMENT ON COLUMN vehicle_transmission.file_id IS 'Identificador do arquivo associado à transmissão.';
COMMENT ON COLUMN vehicle_transmission.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN vehicle_transmission.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN vehicle_transmission.enabled IS 'Indica se a transmissão está habilitada.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT vehicle_transmission_pkey  ON "vehicle_transmission" IS 'Chave primária da tabela vehicle_transmission, identificador único da transmissão.';
COMMENT ON CONSTRAINT vehicle_transmission_unique  ON "vehicle_transmission" IS 'Restrição de unicidade para o nome da transmissão.';
COMMENT ON CONSTRAINT vehicle_transmission_to_file_fk  ON "vehicle_transmission" IS 'Chave estrangeira referenciando a tabela file para o arquivo associado à transmissão.';