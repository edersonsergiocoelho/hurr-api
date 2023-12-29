CREATE TABLE IF NOT EXISTS payment_status
(
    payment_status_id UUID DEFAULT uuid_generate_v4(),
    payment_status_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    file_id UUID,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT payment_status_pkey PRIMARY KEY (payment_status_id),
    CONSTRAINT payment_status_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);