CREATE TABLE IF NOT EXISTS vehicle_brand
(
    vehicle_brand_id UUID DEFAULT uuid_generate_v4(),
    vehicle_brand_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    file_id UUID,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT vehicle_brand_pkey PRIMARY KEY (vehicle_brand_id),
    CONSTRAINT vehicle_brand_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);