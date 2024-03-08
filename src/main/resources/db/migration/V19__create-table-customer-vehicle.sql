CREATE TABLE IF NOT EXISTS customer_vehicle
(
    customer_vehicle_id UUID DEFAULT uuid_generate_v4(),
    customer_id UUID NOT NULL,
    vehicle_id UUID NOT NULL,
    vehicle_model_id UUID NOT NULL,
    vehicle_color_id UUID NOT NULL,
    vehicle_fuel_type_id UUID NOT NULL,
    vehicle_transmission_id UUID NOT NULL,
    description TEXT NOT NULL,
    license_plate CHARACTER VARYING(10) NOT NULL UNIQUE,
    renavam CHARACTER VARYING(20) NOT NULL UNIQUE,
    chassis CHARACTER VARYING(20) NOT NULL UNIQUE,
    year_of_manufacture INTEGER NOT NULL,
    year_of_the_car INTEGER NOT NULL,
    daily_rate NUMERIC(13,2) NOT NULL,
    cleaning_fee NUMERIC(13,2) NOT NULL,
    unlimited_mileage BOOLEAN NOT NULL,
    limited_mileage BOOLEAN NOT NULL,
    limited_mileage_included INTEGER,
    limited_mileage_value NUMERIC(13,2),
    deliver_to_address BOOLEAN NOT NULL,
    pick_up_at_address BOOLEAN NOT NULL,
    mileage_fee_delivery NUMERIC(13,2),
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled BOOLEAN NOT NULL DEFAULT true,
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
        ON DELETE NO ACTION
);