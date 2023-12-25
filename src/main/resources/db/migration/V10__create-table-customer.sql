DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_type
        WHERE typname = 'customer_type' AND typtype = 'e'
    ) THEN
        CREATE TYPE customer_type AS ENUM ('CUSTOMER', 'OWNER');
    END IF;
END
$$;

CREATE TABLE IF NOT EXISTS customer
(
    customer_id UUID DEFAULT uuid_generate_v4(),
    first_name CHARACTER VARYING(100) NOT NULL,
    last_name CHARACTER VARYING(100) NOT NULL,
    email CHARACTER VARYING(100) NOT NULL UNIQUE,
    ddi_phone CHARACTER VARYING(20) NOT NULL,
    phone CHARACTER VARYING(20) NOT NULL,
    date_of_birth DATE NOT NULL,
    customer_type customer_type NOT NULL DEFAULT 'CUSTOMER',
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
);