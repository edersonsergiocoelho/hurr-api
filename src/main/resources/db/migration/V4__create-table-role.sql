CREATE TABLE IF NOT EXISTS role
(
    role_id UUID DEFAULT uuid_generate_v4(),
    role_name CHARACTER VARYING(100) NOT NULL UNIQUE,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT role_pkey PRIMARY KEY (role_id)
);