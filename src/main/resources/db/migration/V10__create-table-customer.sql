CREATE TABLE IF NOT EXISTS customer
(
    customer_id UUID DEFAULT uuid_generate_v4(),
    first_name CHARACTER VARYING(100),
    last_name CHARACTER VARYING(100),
    email CHARACTER VARYING(300) NOT NULL UNIQUE,
    ddi_phone CHARACTER VARYING(10),
    phone CHARACTER VARYING(20),
    date_of_birth DATE,
    cpf CHARACTER VARYING(20),
    identity_number CHARACTER VARYING(20),
    driver_license_registration_number CHARACTER VARYING(20),
    driver_license_category CHARACTER VARYING(10),
    driver_license_expiration_date TIMESTAMP WITHOUT TIME ZONE,
    customer_type CHARACTER VARYING(50) NOT NULL DEFAULT 'CUSTOMER',
    email_verification_code CHARACTER VARYING(6),
    email_validated boolean NOT NULL DEFAULT false,
    phone_validated boolean NOT NULL DEFAULT false,
    driver_license_validated boolean NOT NULL DEFAULT false,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
);