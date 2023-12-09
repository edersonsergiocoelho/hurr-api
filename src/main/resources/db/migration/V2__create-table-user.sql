CREATE TABLE IF NOT EXISTS "user"
(
    user_id UUID DEFAULT uuid_generate_v4(),
    provider_user_id CHARACTER VARYING(100),
    email CHARACTER VARYING(200),
    display_name CHARACTER VARYING(200),
    password CHARACTER VARYING(200),
    provider CHARACTER VARYING(50),
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT user_pkey PRIMARY KEY (user_id)
);