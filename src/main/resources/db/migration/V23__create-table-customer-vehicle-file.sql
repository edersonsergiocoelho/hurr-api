-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "customer_vehicle_file" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer_vehicle_file
(
    -- Identificador único do arquivo do veículo do cliente
    customer_vehicle_file_id UUID DEFAULT uuid_generate_v4(),

    -- Identificador do veículo do cliente (obrigatório)
    customer_vehicle_id UUID NOT NULL,

    -- Tipo de conteúdo do arquivo (obrigatório)
    content_type VARCHAR(50) NOT NULL,

    -- Nome original do arquivo (obrigatório)
    original_file_name VARCHAR(1000) NOT NULL,

    -- Dados do arquivo em formato byte array (obrigatório)
    data_as_byte_array BYTEA NOT NULL,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o registro está habilitado
    enabled BOOLEAN NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'customer_vehicle_file'
    CONSTRAINT customer_vehicle_file_pkey PRIMARY KEY (customer_vehicle_file_id),

    -- Define a chave estrangeira para o veículo do cliente associado
    CONSTRAINT customer_vehicle_file_to_customer_vehicle_fk FOREIGN KEY (customer_vehicle_id)
        REFERENCES customer_vehicle (customer_vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre os arquivos associados aos veículos dos clientes
COMMENT ON TABLE customer_vehicle_file IS 'Tabela que armazena informações sobre arquivos associados aos veículos dos clientes.';

-- Comentários para cada campo
COMMENT ON COLUMN customer_vehicle_file.customer_vehicle_file_id IS 'Identificador único do arquivo do veículo do cliente.';
COMMENT ON COLUMN customer_vehicle_file.customer_vehicle_id IS 'Identificador do veículo do cliente associado.';
COMMENT ON COLUMN customer_vehicle_file.content_type IS 'Tipo de conteúdo do arquivo.';
COMMENT ON COLUMN customer_vehicle_file.original_file_name IS 'Nome original do arquivo.';
COMMENT ON COLUMN customer_vehicle_file.data_as_byte_array IS 'Dados do arquivo em formato byte array.';
COMMENT ON COLUMN customer_vehicle_file.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN customer_vehicle_file.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN customer_vehicle_file.enabled IS 'Indica se o registro está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT customer_vehicle_file_pkey ON customer_vehicle_file IS 'Chave primária da tabela customer_vehicle_file, identificador único do arquivo do veículo do cliente.';
COMMENT ON CONSTRAINT customer_vehicle_file_to_customer_vehicle_fk ON customer_vehicle_file IS 'Chave estrangeira referenciando a tabela customer_vehicle para o veículo do cliente associado.';