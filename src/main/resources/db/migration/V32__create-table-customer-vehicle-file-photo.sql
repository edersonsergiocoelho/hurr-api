CREATE TABLE IF NOT EXISTS customer_vehicle_file_photo
(
    customer_vehicle_file_photo_id UUID DEFAULT uuid_generate_v4(), -- Identificador único da foto do veículo do cliente
    customer_vehicle_id UUID NOT NULL, -- Identificador único do veículo do cliente associado
    content_type VARCHAR(50) NOT NULL, -- Tipo de conteúdo do arquivo (MIME type)
    original_file_name VARCHAR(1000) NOT NULL, -- Nome original do arquivo
    data_as_byte_array BYTEA NOT NULL, -- Dados do arquivo em formato de array de bytes
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data de criação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data de modificação do registro
    enabled boolean NOT NULL DEFAULT true, -- Indicador de ativação do registro
    CONSTRAINT customer_vehicle_file_photo_pkey PRIMARY KEY (customer_vehicle_file_photo_id),
    CONSTRAINT customer_vehicle_file_photo_to_customer_vehicle_fk FOREIGN KEY (customer_vehicle_id)
        REFERENCES customer_vehicle (customer_vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE customer_vehicle_file_photo IS 'Tabela para armazenar fotos relacionadas aos veículos dos clientes';

COMMENT ON COLUMN customer_vehicle_file_photo.customer_vehicle_file_photo_id IS 'Identificador único da foto do veículo do cliente';
COMMENT ON COLUMN customer_vehicle_file_photo.customer_vehicle_id IS 'Identificador único do veículo do cliente associado';
COMMENT ON COLUMN customer_vehicle_file_photo.content_type IS 'Tipo de conteúdo do arquivo (MIME type)';
COMMENT ON COLUMN customer_vehicle_file_photo.original_file_name IS 'Nome original do arquivo';
COMMENT ON COLUMN customer_vehicle_file_photo.data_as_byte_array IS 'Dados do arquivo em formato de array de bytes';
COMMENT ON COLUMN customer_vehicle_file_photo.created_date IS 'Data de criação do registro';
COMMENT ON COLUMN customer_vehicle_file_photo.modified_date IS 'Data de modificação do registro';
COMMENT ON COLUMN customer_vehicle_file_photo.enabled IS 'Indicador de ativação do registro';
