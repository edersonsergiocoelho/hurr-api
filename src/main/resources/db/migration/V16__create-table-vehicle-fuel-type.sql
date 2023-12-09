CREATE TABLE IF NOT EXISTS vehicle_fuel_type
(
    vehicle_fuel_type_id UUID DEFAULT uuid_generate_v4(),
    vehicle_fuel_type_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT vehicle_fuel_type_pkey PRIMARY KEY (vehicle_fuel_type_id)
);