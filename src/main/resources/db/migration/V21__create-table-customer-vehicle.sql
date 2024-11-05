-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "customer_vehicle" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer_vehicle
(
    -- Identificador único do veículo do cliente
    customer_vehicle_id UUID DEFAULT uuid_generate_v4(), 
    
    -- Identificador único do cliente associado
    customer_id UUID NOT NULL, 
    
    -- Identificador único do veículo associado
    vehicle_id UUID NOT NULL, 
    
    -- Identificador único do modelo do veículo associado
    vehicle_model_id UUID NOT NULL, 
    
    -- Identificador único da cor do veículo associado
    vehicle_color_id UUID NOT NULL, 
    
    -- Identificador único do tipo de combustível do veículo associado
    vehicle_fuel_type_id UUID NOT NULL, 
    
    -- Identificador único da transmissão do veículo associado
    vehicle_transmission_id UUID NOT NULL, 
    
    -- Descrição do veículo
    description TEXT NOT NULL, 
    
    -- Placa do veículo
    license_plate CHARACTER VARYING(10) NOT NULL, 
    
    -- RENAVAM do veículo
    renavam CHARACTER VARYING(20) NOT NULL, 
    
    -- Identificador único do estado do RENAVAM
    renavam_state_id UUID NOT NULL, 
    
    -- Chassi do veículo
    chassis CHARACTER VARYING(20) NOT NULL, 
    
    -- Ano de fabricação do veículo
    year_of_manufacture INTEGER NOT NULL, 
    
    -- Ano do veículo
    year_of_the_car INTEGER NOT NULL, 
    
    -- Valor do veículo
    vehicle_value NUMERIC(13,2) NOT NULL, 
    
    -- Quilometragem do veículo no momento da criação do registro
    mileage_created INTEGER NOT NULL, 
    
    -- Taxa diária do veículo
    daily_rate NUMERIC(13,2) NOT NULL, 
    
    -- Taxa de limpeza do veículo
    cleaning_fee NUMERIC(13,2) NOT NULL, 
    
    -- Indica se a quilometragem é ilimitada
    unlimited_mileage BOOLEAN NOT NULL, 
    
    -- Indica se a quilometragem é limitada
    limited_mileage BOOLEAN NOT NULL, 
    
    -- Quilometragem limitada incluída
    limited_mileage_included INTEGER, 
    
    -- Valor da quilometragem limitada
    limited_mileage_value NUMERIC(13,2), 
    
    -- Indica se o veículo pode ser entregue no endereço
    deliver_to_address BOOLEAN NOT NULL DEFAULT false, 
    
    -- Taxa de quilometragem para entrega
    mileage_fee_delivery NUMERIC(13,2), 
    
    -- Indica se o veículo pode ser retirado no endereço
    pick_up_at_address BOOLEAN NOT NULL DEFAULT false, 
    
    -- Taxa de quilometragem para retirada
    mileage_fee_pick_up NUMERIC(13,2), 
    
    -- Código do veículo do cliente
    code CHARACTER VARYING(100) NOT NULL, 
    
    -- Indica se o veículo do cliente foi validado
    customer_vehicle_validated BOOLEAN NOT NULL DEFAULT false, 
    
    -- Status do anúncio do veículo
    advertisement_status CHARACTER VARYING(20) NOT NULL DEFAULT 'DRAFT', 
    
    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, 
    
    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, 
    
    -- Indica se o registro está ativo
    enabled BOOLEAN NOT NULL DEFAULT true, 
    
    -- Define a chave primária da tabela 'customer_vehicle'
    CONSTRAINT customer_vehicle_id_pkey PRIMARY KEY (customer_vehicle_id),

    -- Restrição de unicidade composta para chassis, renavam, placa e código
    CONSTRAINT customer_vehicle_unique UNIQUE (chassis, renavam, license_plate, code), 
    
    -- Define a chave estrangeira para o cliente associado
    CONSTRAINT customer_vehicle_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o veículo associado
    CONSTRAINT customer_vehicle_to_vehicle_fk FOREIGN KEY (vehicle_id)
        REFERENCES vehicle (vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o modelo do veículo associado
    CONSTRAINT customer_vehicle_to_vehicle_model_fk FOREIGN KEY (vehicle_model_id)
        REFERENCES vehicle_model (vehicle_model_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para a cor do veículo associado
    CONSTRAINT customer_vehicle_to_vehicle_color_fk FOREIGN KEY (vehicle_color_id)
        REFERENCES vehicle_color (vehicle_color_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o tipo de combustível do veículo associado
    CONSTRAINT customer_vehicle_to_vehicle_fuel_type_fk FOREIGN KEY (vehicle_fuel_type_id)
        REFERENCES vehicle_fuel_type (vehicle_fuel_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para a transmissão do veículo associado
    CONSTRAINT customer_vehicle_to_vehicle_transmission_fk FOREIGN KEY (vehicle_transmission_id)
        REFERENCES vehicle_transmission (vehicle_transmission_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o estado do RENAVAM
    CONSTRAINT customer_vehicle_to_state_fk FOREIGN KEY (renavam_state_id)
        REFERENCES state (state_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentário para a tabela
COMMENT ON TABLE customer_vehicle IS 'Tabela para armazenar informações dos veículos dos clientes';

-- Comentários para cada campo
COMMENT ON COLUMN customer_vehicle.customer_vehicle_id IS 'Identificador único do veículo do cliente';
COMMENT ON COLUMN customer_vehicle.customer_id IS 'Identificador único do cliente associado';
COMMENT ON COLUMN customer_vehicle.vehicle_id IS 'Identificador único do veículo associado';
COMMENT ON COLUMN customer_vehicle.vehicle_model_id IS 'Identificador único do modelo do veículo associado';
COMMENT ON COLUMN customer_vehicle.vehicle_color_id IS 'Identificador único da cor do veículo associado';
COMMENT ON COLUMN customer_vehicle.vehicle_fuel_type_id IS 'Identificador único do tipo de combustível do veículo associado';
COMMENT ON COLUMN customer_vehicle.vehicle_transmission_id IS 'Identificador único da transmissão do veículo associado';
COMMENT ON COLUMN customer_vehicle.description IS 'Descrição do veículo';
COMMENT ON COLUMN customer_vehicle.license_plate IS 'Placa do veículo';
COMMENT ON COLUMN customer_vehicle.renavam IS 'RENAVAM do veículo';
COMMENT ON COLUMN customer_vehicle.renavam_state_id IS 'Identificador único do estado do RENAVAM';
COMMENT ON COLUMN customer_vehicle.chassis IS 'Chassi do veículo';
COMMENT ON COLUMN customer_vehicle.year_of_manufacture IS 'Ano de fabricação do veículo';
COMMENT ON COLUMN customer_vehicle.year_of_the_car IS 'Ano do veículo';
COMMENT ON COLUMN customer_vehicle.vehicle_value IS 'Valor do veículo';
COMMENT ON COLUMN customer_vehicle.mileage_created IS 'Quilometragem do veículo no momento da criação do registro';
COMMENT ON COLUMN customer_vehicle.daily_rate IS 'Taxa diária do veículo';
COMMENT ON COLUMN customer_vehicle.cleaning_fee IS 'Taxa de limpeza do veículo';
COMMENT ON COLUMN customer_vehicle.unlimited_mileage IS 'Indica se a quilometragem é ilimitada';
COMMENT ON COLUMN customer_vehicle.limited_mileage IS 'Indica se a quilometragem é limitada';
COMMENT ON COLUMN customer_vehicle.limited_mileage_included IS 'Quilometragem limitada incluída';
COMMENT ON COLUMN customer_vehicle.limited_mileage_value IS 'Valor da quilometragem limitada';
COMMENT ON COLUMN customer_vehicle.deliver_to_address IS 'Indica se o veículo pode ser entregue no endereço';
COMMENT ON COLUMN customer_vehicle.mileage_fee_delivery IS 'Taxa de quilometragem para entrega';
COMMENT ON COLUMN customer_vehicle.pick_up_at_address IS 'Indica se o veículo pode ser retirado no endereço';
COMMENT ON COLUMN customer_vehicle.mileage_fee_pick_up IS 'Taxa de quilometragem para retirada';
COMMENT ON COLUMN customer_vehicle.code IS 'Código do veículo do cliente';
COMMENT ON COLUMN customer_vehicle.customer_vehicle_validated IS 'Indica se o veículo do cliente foi validado';
COMMENT ON COLUMN customer_vehicle.advertisement_status IS 'Status do anúncio do veículo';
COMMENT ON COLUMN customer_vehicle.created_date IS 'Data de criação do registro';
COMMENT ON COLUMN customer_vehicle.modified_date IS 'Data de modificação do registro';
COMMENT ON COLUMN customer_vehicle.enabled IS 'Indica se o registro está ativo';

-- Comentários sobre as restrições

COMMENT ON CONSTRAINT customer_vehicle_id_pkey ON customer_vehicle IS 'Chave primária da tabela customer_vehicle, identificador único do veículo do cliente.';
COMMENT ON CONSTRAINT customer_vehicle_unique ON customer_vehicle IS 'Restrição de unicidade composta para chassis, renavam, placa e código.';
COMMENT ON CONSTRAINT customer_vehicle_to_customer_fk ON customer_vehicle IS 'Chave estrangeira que referencia a tabela customer, associando o veículo ao cliente.';
COMMENT ON CONSTRAINT customer_vehicle_to_vehicle_fk ON customer_vehicle IS 'Chave estrangeira que referencia a tabela vehicle, associando o veículo ao cliente.';
COMMENT ON CONSTRAINT customer_vehicle_to_vehicle_model_fk ON customer_vehicle IS 'Chave estrangeira que referencia a tabela vehicle_model, associando o modelo do veículo ao cliente.';
COMMENT ON CONSTRAINT customer_vehicle_to_vehicle_color_fk ON customer_vehicle IS 'Chave estrangeira que referencia a tabela vehicle_color, associando a cor do veículo ao cliente.';
COMMENT ON CONSTRAINT customer_vehicle_to_vehicle_fuel_type_fk ON customer_vehicle IS 'Chave estrangeira que referencia a tabela vehicle_fuel_type, associando o tipo de combustível ao veículo do cliente.';
COMMENT ON CONSTRAINT customer_vehicle_to_vehicle_transmission_fk ON customer_vehicle IS 'Chave estrangeira que referencia a tabela vehicle_transmission, associando a transmissão ao veículo do cliente.';
COMMENT ON CONSTRAINT customer_vehicle_to_state_fk ON customer_vehicle IS 'Chave estrangeira que referencia a tabela state, associando o estado do RENAVAM ao veículo do cliente.';