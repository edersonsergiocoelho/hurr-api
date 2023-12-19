CREATE TABLE IF NOT EXISTS vehicle_transmission
(
    vehicle_transmission_id UUID DEFAULT uuid_generate_v4(),
    vehicle_transmission_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT vehicle_transmission_pkey PRIMARY KEY (vehicle_transmission_id)
);