CREATE TABLE IF NOT EXISTS user_role
(
    role_id UUID NOT NULL,
    user_id UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT user_role_pkey PRIMARY KEY (role_id, user_id),
    CONSTRAINT user_role_to_role_fk FOREIGN KEY (role_id)
        REFERENCES role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_role_to_user_fk FOREIGN KEY (user_id)
        REFERENCES "user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);