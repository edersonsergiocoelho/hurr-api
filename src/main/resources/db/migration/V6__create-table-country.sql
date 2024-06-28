CREATE TABLE IF NOT EXISTS country
(
    country_id UUID DEFAULT uuid_generate_v4(),
    country_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    service_available boolean NOT NULL DEFAULT false,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT country_pkey PRIMARY KEY (country_id)
);