CREATE TABLE IF NOT EXISTS hurr."user"
(
    user_id UUID DEFAULT uuid_generate_v4(),
    provider_user_id character varying(100),
    email character varying(200),
    enabled boolean,
    display_name character varying(200),
    created_date timestamp without time zone NOT NULL,
    modified_date timestamp without time zone,
    password character varying(200),
    provider character varying(50),
    CONSTRAINT user_pkey PRIMARY KEY (user_id)
);