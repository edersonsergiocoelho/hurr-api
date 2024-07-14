CREATE TABLE IF NOT EXISTS customer_vehicle
(
    customer_vehicle_id UUID DEFAULT uuid_generate_v4(), -- Identificador único do veículo do cliente
    customer_id UUID NOT NULL, -- Identificador único do cliente associado
    vehicle_id UUID NOT NULL, -- Identificador único do veículo associado
    vehicle_model_id UUID NOT NULL, -- Identificador único do modelo do veículo associado
    vehicle_color_id UUID NOT NULL, -- Identificador único da cor do veículo associado
    vehicle_fuel_type_id UUID NOT NULL, -- Identificador único do tipo de combustível do veículo associado
    vehicle_transmission_id UUID NOT NULL, -- Identificador único da transmissão do veículo associado
    description TEXT NOT NULL, -- Descrição do veículo
    license_plate CHARACTER VARYING(10) NOT NULL UNIQUE, -- Placa do veículo
    renavam CHARACTER VARYING(20) NOT NULL UNIQUE, -- RENAVAM do veículo
    renavam_state_id UUID NOT NULL, -- Identificador único do estado do RENAVAM
    chassis CHARACTER VARYING(20) NOT NULL UNIQUE, -- Chassi do veículo
    year_of_manufacture INTEGER NOT NULL, -- Ano de fabricação do veículo
    year_of_the_car INTEGER NOT NULL, -- Ano do veículo
    vehicle_value NUMERIC(13,2) NOT NULL, -- Valor do veículo
    mileage_created INTEGER NOT NULL, -- Quilometragem do veículo no momento da criação do registro
    daily_rate NUMERIC(13,2) NOT NULL, -- Taxa diária do veículo
    cleaning_fee NUMERIC(13,2) NOT NULL, -- Taxa de limpeza do veículo
    unlimited_mileage BOOLEAN NOT NULL, -- Indica se a quilometragem é ilimitada
    limited_mileage BOOLEAN NOT NULL, -- Indica se a quilometragem é limitada
    limited_mileage_included INTEGER, -- Quilometragem limitada incluída
    limited_mileage_value NUMERIC(13,2), -- Valor da quilometragem limitada
    deliver_to_address BOOLEAN NOT NULL DEFAULT false, -- Indica se o veículo pode ser entregue no endereço
    mileage_fee_delivery NUMERIC(13,2), -- Taxa de quilometragem para entrega
    pick_up_at_address BOOLEAN NOT NULL DEFAULT false, -- Indica se o veículo pode ser retirado no endereço
    mileage_fee_pick_up NUMERIC(13,2), -- Taxa de quilometragem para retirada
    code CHARACTER VARYING(100) NOT NULL UNIQUE, -- Código do veículo do cliente
    customer_vehicle_validated BOOLEAN NOT NULL DEFAULT false, -- Indica se o veículo do cliente foi validado
    advertisement_status CHARACTER VARYING(20) NOT NULL DEFAULT 'DRAFT', -- Status do anúncio do veículo
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data de criação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data de modificação do registro
    enabled BOOLEAN NOT NULL DEFAULT true, -- Indica se o registro está ativo
    CONSTRAINT customer_vehicle_id_pkey PRIMARY KEY (customer_vehicle_id),
    CONSTRAINT customer_vehicle_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_vehicle_to_vehicle_fk FOREIGN KEY (vehicle_id)
        REFERENCES vehicle (vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_vehicle_to_vehicle_model_fk FOREIGN KEY (vehicle_model_id)
        REFERENCES vehicle_model (vehicle_model_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_vehicle_to_vehicle_color_fk FOREIGN KEY (vehicle_color_id)
        REFERENCES vehicle_color (vehicle_color_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_vehicle_to_vehicle_fuel_type_fk FOREIGN KEY (vehicle_fuel_type_id)
        REFERENCES vehicle_fuel_type (vehicle_fuel_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_vehicle_to_vehicle_transmission_fk FOREIGN KEY (vehicle_transmission_id)
        REFERENCES vehicle_transmission (vehicle_transmission_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_vehicle_to_state_fk FOREIGN KEY (renavam_state_id)
        REFERENCES state (state_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE customer_vehicle IS 'Tabela para armazenar informações dos veículos dos clientes';

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