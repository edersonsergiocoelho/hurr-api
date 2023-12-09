CREATE TABLE IF NOT EXISTS vehicle_categories
(
    vehicle_categories_id UUID DEFAULT uuid_generate_v4(),
    vehicle_categories_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT vehicle_categories_pkey PRIMARY KEY (vehicle_categories_id)
);