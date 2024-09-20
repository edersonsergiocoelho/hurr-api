CREATE TABLE IF NOT EXISTS city
(
    city_id UUID DEFAULT uuid_generate_v4(),
    city_name CHARACTER VARYING(100) NOT NULL,
    state_id UUID NOT NULL,
    service_available boolean NOT NULL DEFAULT false,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT city_pkey PRIMARY KEY (city_id),
    CONSTRAINT city_city_name_key UNIQUE (city_name, state_id),
    CONSTRAINT city_to_state_fk FOREIGN KEY (state_id)
        REFERENCES state (state_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);