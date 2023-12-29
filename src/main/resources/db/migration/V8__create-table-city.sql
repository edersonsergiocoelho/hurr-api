CREATE TABLE IF NOT EXISTS city
(
    city_id UUID DEFAULT uuid_generate_v4(),
    city_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    country_id UUID NOT NULL,
    state_id UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT city_pkey PRIMARY KEY (city_id),
    CONSTRAINT country_id FOREIGN KEY (country_id)
        REFERENCES country (country_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT state_id FOREIGN KEY (state_id)
        REFERENCES state (state_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);