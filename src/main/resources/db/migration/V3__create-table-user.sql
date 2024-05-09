CREATE TABLE IF NOT EXISTS "user"
(
    user_id UUID DEFAULT uuid_generate_v4(),
    provider_user_id CHARACTER VARYING(100),
    email CHARACTER VARYING(200),
    display_name CHARACTER VARYING(200) NOT NULL,
    password CHARACTER VARYING(200) NOT NULL,
    forgot_password_verification_code CHARACTER VARYING(6),
    forgot_password_validated BOOLEAN NOT NULL DEFAULT false,
    provider CHARACTER VARYING(50) NOT NULL,
    photo_file_id UUID,
    photo_validated boolean NOT NULL DEFAULT false,
    image_url TEXT,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT user_pkey PRIMARY KEY (user_id),
    CONSTRAINT user_to_file_fk FOREIGN KEY (photo_file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);