CREATE TABLE IF NOT EXISTS customer
(
    customer_id UUID DEFAULT uuid_generate_v4(),
    first_name CHARACTER VARYING(100),
    last_name CHARACTER VARYING(100),
    customer_type CHARACTER VARYING(50) NOT NULL DEFAULT 'CUSTOMER',
    email CHARACTER VARYING(300) NOT NULL UNIQUE,
    email_verification_code CHARACTER VARYING(6),
    email_validated boolean NOT NULL DEFAULT false,
    ddi_phone CHARACTER VARYING(10),
    phone CHARACTER VARYING(20),
    phone_verification_code CHARACTER VARYING(6),
    phone_validated boolean NOT NULL DEFAULT false,
    date_of_birth DATE,
    cpf CHARACTER VARYING(20),
    identity_number CHARACTER VARYING(20),
    identity_number_issuing_body CHARACTER VARYING(20),
    identity_number_issuing_body_uf CHARACTER VARYING(10),
    identity_number_validated boolean NOT NULL DEFAULT false,
    identity_number_file_id UUID,
    driver_license_registration_number CHARACTER VARYING(20),
    driver_license_category CHARACTER VARYING(10),
    driver_license_first_license_date TIMESTAMP WITHOUT TIME ZONE,
    driver_license_expiration_date TIMESTAMP WITHOUT TIME ZONE,
    driver_license_issue_date TIMESTAMP WITHOUT TIME ZONE,
    driver_license_issue_uf CHARACTER VARYING(10),
    driver_license_validated boolean NOT NULL DEFAULT false,
    driver_license_file_id UUID,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT customer_pkey PRIMARY KEY (customer_id),
    CONSTRAINT customer_driver_license_to_file_fk FOREIGN KEY (driver_license_file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_identity_number_to_file_fk FOREIGN KEY (identity_number_file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);