CREATE TABLE IF NOT EXISTS vehicle_color
(
    vehicle_color_id UUID DEFAULT uuid_generate_v4(),
    vehicle_color_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT vehicle_color_pkey PRIMARY KEY (vehicle_color_id)
);