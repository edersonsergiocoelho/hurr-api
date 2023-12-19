CREATE TABLE IF NOT EXISTS vehicle_model
(
    vehicle_model_id UUID DEFAULT uuid_generate_v4(),
    vehicle_model_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    vehicle_id UUID NOT NULL,
    vehicle_category_id UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT vehicle_model_pkey PRIMARY KEY (vehicle_model_id),
    CONSTRAINT vehicle_id FOREIGN KEY (vehicle_id)
        REFERENCES vehicle (vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);