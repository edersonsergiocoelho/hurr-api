CREATE TABLE IF NOT EXISTS hurr.role
(
    role_id UUID DEFAULT uuid_generate_v4(),
    name character varying(100) NOT NULL,
    enabled boolean NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role_id)
);