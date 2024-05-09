CREATE TABLE IF NOT EXISTS address_address_type
(
    address_id UUID NOT NULL,
    address_type_id UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT address_address_type_pkey PRIMARY KEY (address_type_id, address_id),
        CONSTRAINT address_address_type_to_address_fk FOREIGN KEY (address_id)
        REFERENCES address (address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT address_address_type_to_address_type_fk FOREIGN KEY (address_type_id)
        REFERENCES address_type (address_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);