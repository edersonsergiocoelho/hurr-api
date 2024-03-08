CREATE TABLE IF NOT EXISTS city
(
    city_id UUID DEFAULT uuid_generate_v4(),
    city_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    state_id UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT city_pkey PRIMARY KEY (city_id),
    CONSTRAINT city_to_state_fk FOREIGN KEY (state_id)
        REFERENCES state (state_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);