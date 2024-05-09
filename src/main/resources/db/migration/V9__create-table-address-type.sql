CREATE TABLE IF NOT EXISTS address_type
(
    address_type_id UUID DEFAULT uuid_generate_v4(),
    address_type_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT address_type_pkey PRIMARY KEY (address_type_id)
);