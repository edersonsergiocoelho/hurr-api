CREATE TABLE IF NOT EXISTS vehicle
(
    vehicle_id UUID DEFAULT uuid_generate_v4(),
    vehicle_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    vehicle_brand_id UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT vehicle_pkey PRIMARY KEY (vehicle_id),
    CONSTRAINT vehicle_brand_id FOREIGN KEY (vehicle_brand_id)
        REFERENCES vehicle_brand (vehicle_brand_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);