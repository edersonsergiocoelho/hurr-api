CREATE TABLE IF NOT EXISTS customer_vehicle
(
    customer_vehicle_id UUID DEFAULT uuid_generate_v4(),
    customer_id UUID NOT NULL,
    vehicle_id UUID NOT NULL,
    vehicle_model_id UUID NOT NULL,
    vehicle_color_id UUID NOT NULL,
    vehicle_fuel_type_id UUID NOT NULL,
    license_plate CHARACTER VARYING(10) NOT NULL UNIQUE,
    renavam CHARACTER VARYING(20) NOT NULL UNIQUE,
    chassis CHARACTER VARYING(20) NOT NULL UNIQUE,
    year_of_manufacture INTEGER NOT NULL,
    year_of_the_car INTEGER NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT customer_vehicle_id_pkey PRIMARY KEY (customer_vehicle_id),
    CONSTRAINT customer_id FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vehicle_id FOREIGN KEY (vehicle_id)
        REFERENCES vehicle (vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vehicle_model_id FOREIGN KEY (vehicle_model_id)
        REFERENCES vehicle_model (vehicle_model_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vehicle_color_id FOREIGN KEY (vehicle_color_id)
        REFERENCES vehicle_color (vehicle_color_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vehicle_fuel_type_id FOREIGN KEY (vehicle_fuel_type_id)
        REFERENCES vehicle_fuel_type (vehicle_fuel_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);