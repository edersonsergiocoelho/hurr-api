-- Cria a tabela "customer_vehicle_file_insurance" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer_vehicle_file_insurance
(
    -- Identificador único do arquivo de seguro do veículo do cliente (obrigatório)
    customer_vehicle_file_insurance_id UUID DEFAULT uuid_generate_v4(),

    -- Identificador único do veículo do cliente associado (obrigatório)
    customer_vehicle_id UUID NOT NULL, 

    -- Tipo de conteúdo do arquivo (MIME type) (obrigatório)
    content_type VARCHAR(50) NOT NULL, 

    -- Nome original do arquivo (obrigatório)
    original_file_name VARCHAR(1000) NOT NULL, 

    -- Dados do arquivo em formato de array de bytes (obrigatório)
    data_as_byte_array BYTEA NOT NULL, 

    -- Data de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, 

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, 

    -- Indicador de ativação do registro (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true, 

    -- Chave primária da tabela 'customer_vehicle_file_insurance'
    CONSTRAINT customer_vehicle_file_insurance_pkey PRIMARY KEY (customer_vehicle_file_insurance_id),

    -- Chave estrangeira para a tabela customer_vehicle
    CONSTRAINT customer_vehicle_file_insurance_to_customer_vehicle_fk FOREIGN KEY (customer_vehicle_id)
        REFERENCES customer_vehicle (customer_vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar documentos de seguro relacionados aos veículos dos clientes
COMMENT ON TABLE customer_vehicle_file_insurance IS 'Tabela para armazenar documentos de seguro relacionados aos veículos dos clientes.';

-- Comentários para cada campo
COMMENT ON COLUMN customer_vehicle_file_insurance.customer_vehicle_file_insurance_id IS 'Identificador único do documento de seguro do veículo do cliente.';
COMMENT ON COLUMN customer_vehicle_file_insurance.customer_vehicle_id IS 'Identificador único do veículo do cliente associado.';
COMMENT ON COLUMN customer_vehicle_file_insurance.content_type IS 'Tipo de conteúdo do arquivo (MIME type).';
COMMENT ON COLUMN customer_vehicle_file_insurance.original_file_name IS 'Nome original do arquivo.';
COMMENT ON COLUMN customer_vehicle_file_insurance.data_as_byte_array IS 'Dados do arquivo em formato de array de bytes.';
COMMENT ON COLUMN customer_vehicle_file_insurance.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN customer_vehicle_file_insurance.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN customer_vehicle_file_insurance.enabled IS 'Indicador de ativação do registro.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT customer_vehicle_file_insurance_pkey ON customer_vehicle_file_insurance IS 'Chave primária da tabela customer_vehicle_file_insurance, identificador único do documento de seguro.';
COMMENT ON CONSTRAINT customer_vehicle_file_insurance_to_customer_vehicle_fk ON customer_vehicle_file_insurance IS 'Chave estrangeira referenciando a tabela customer_vehicle para o veículo associado.';