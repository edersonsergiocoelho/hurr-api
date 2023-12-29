CREATE TABLE IF NOT EXISTS customer_vehicle_file
(
    customer_vehicle_file_id UUID DEFAULT uuid_generate_v4(),
    customer_vehicle_id UUID NOT NULL,
    contentType VARCHAR(50) NOT NULL,
    originalFileName VARCHAR(1000) NOT NULL,
    dataAsByteArray BYTEA NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT customer_vehicle_file_pkey PRIMARY KEY (customer_vehicle_file_id),
    CONSTRAINT customer_vehicle_id FOREIGN KEY (customer_vehicle_id)
        REFERENCES customer_vehicle (customer_vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);