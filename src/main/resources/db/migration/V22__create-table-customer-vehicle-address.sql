-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "customer_vehicle_address" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer_vehicle_address
(
    -- Identificador único do endereço do veículo do cliente
    customer_vehicle_address_id UUID DEFAULT uuid_generate_v4(),

    -- Identificador do veículo do cliente (obrigatório)
    customer_vehicle_id UUID NOT NULL,

    -- Identificador do endereço associado (obrigatório)
    address_id UUID NOT NULL,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o registro está habilitado
    enabled BOOLEAN NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'customer_vehicle_address'
    CONSTRAINT customer_vehicle_address_pkey PRIMARY KEY (customer_vehicle_address_id),

    -- Define a restrição de unicidade para o par (customer_vehicle_id, address_id)
    CONSTRAINT customer_vehicle_address_unique UNIQUE (customer_vehicle_id, address_id),

    -- Define a chave estrangeira para o veículo do cliente associado
    CONSTRAINT customer_vehicle_address_to_customer_vehicle_fk FOREIGN KEY (customer_vehicle_id)
        REFERENCES customer_vehicle (customer_vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o endereço associado
    CONSTRAINT customer_vehicle_address_to_address_fk FOREIGN KEY (address_id)
        REFERENCES address (address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre os endereços associados aos veículos dos clientes
COMMENT ON TABLE customer_vehicle_address IS 'Tabela que armazena informações sobre endereços associados aos veículos dos clientes.';

-- Comentários para cada campo
COMMENT ON COLUMN customer_vehicle_address.customer_vehicle_address_id IS 'Identificador único do endereço do veículo do cliente.';
COMMENT ON COLUMN customer_vehicle_address.customer_vehicle_id IS 'Identificador do veículo do cliente associado.';
COMMENT ON COLUMN customer_vehicle_address.address_id IS 'Identificador do endereço associado.';
COMMENT ON COLUMN customer_vehicle_address.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN customer_vehicle_address.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN customer_vehicle_address.enabled IS 'Indica se o registro está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT customer_vehicle_address_pkey ON customer_vehicle_address IS 'Chave primária da tabela customer_vehicle_address, identificador único do endereço do veículo do cliente.';
COMMENT ON CONSTRAINT customer_vehicle_address_unique ON customer_vehicle_address IS 'Restrição de unicidade para o par (customer_vehicle_id, address_id).';
COMMENT ON CONSTRAINT customer_vehicle_address_to_customer_vehicle_fk ON customer_vehicle_address IS 'Chave estrangeira referenciando a tabela customer_vehicle para o veículo do cliente associado.';
COMMENT ON CONSTRAINT customer_vehicle_address_to_address_fk ON customer_vehicle_address IS 'Chave estrangeira referenciando a tabela address para o endereço associado.';