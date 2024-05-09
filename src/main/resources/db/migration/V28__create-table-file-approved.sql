CREATE TABLE IF NOT EXISTS file_approved
(
    file_approved_id UUID DEFAULT uuid_generate_v4(),
    file_id UUID NOT NULL,
    approved_by UUID,
    reproved_by UUID,
    message TEXT,
    file_table CHARACTER VARYING(100) NOT NULL,
    customer_id UUID,
    user_id UUID,
    file_type CHARACTER VARYING(100) NOT NULL,
    created_by UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    modified_by UUID,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT file_approved_pkey PRIMARY KEY (file_approved_id),
    CONSTRAINT file_approved_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT file_approved_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT file_approved_to_user_fk FOREIGN KEY (user_id)
        REFERENCES "user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);