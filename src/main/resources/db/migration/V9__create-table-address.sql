CREATE TABLE IF NOT EXISTS address (
    address_id UUID DEFAULT uuid_generate_v4(),
    street_address CHARACTER VARYING(100) NOT NULL,
    number INT NOT NULL,
    complement CHARACTER VARYING(100),
    country_id UUID NOT NULL,
    city_id UUID NOT NULL,
    state_id UUID NOT NULL,
    zip_code CHARACTER VARYING(20) NOT NULL,
    address_type CHARACTER VARYING(100) NOT NULL,
    nickname CHARACTER VARYING(100) NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT address_pkey PRIMARY KEY (address_id),
    CONSTRAINT country_id FOREIGN KEY (country_id)
        REFERENCES country (country_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT city_id FOREIGN KEY (city_id)
        REFERENCES city (city_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT state_id FOREIGN KEY (state_id)
        REFERENCES state (state_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);