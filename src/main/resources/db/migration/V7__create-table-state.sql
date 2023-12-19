CREATE TABLE IF NOT EXISTS state
(
    state_id UUID DEFAULT uuid_generate_v4(),
    state_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    country_id UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT state_pkey PRIMARY KEY (state_id),
    CONSTRAINT country_id FOREIGN KEY (country_id)
        REFERENCES country (country_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);