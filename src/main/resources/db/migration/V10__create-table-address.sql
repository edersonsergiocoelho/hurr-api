CREATE TABLE IF NOT EXISTS address (
    address_id UUID DEFAULT uuid_generate_v4(),
    street_address CHARACTER VARYING(100) NOT NULL,
    number INT NOT NULL,
    complement CHARACTER VARYING(100),
    country_id UUID NOT NULL,
    city_id UUID NOT NULL,
    state_id UUID NOT NULL,
    zip_code CHARACTER VARYING(20) NOT NULL,
    nickname CHARACTER VARYING(100) NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT address_pkey PRIMARY KEY (address_id),
    CONSTRAINT address_to_country_fk FOREIGN KEY (country_id)
        REFERENCES country (country_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT address_to_city_fk FOREIGN KEY (city_id)
        REFERENCES city (city_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT address_to_state_fk FOREIGN KEY (state_id)
        REFERENCES state (state_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION);