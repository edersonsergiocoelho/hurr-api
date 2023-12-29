DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_type
        WHERE typname = 'address_type' AND typtype = 'e'
    ) THEN
        CREATE TYPE address_type AS ENUM ('CUSTOMER', 'VEHICLE', 'DELIVERY', 'PICKUP');
    END IF;
END
$$;

CREATE TABLE IF NOT EXISTS customer_vehicle_address
(
    customer_vehicle_address_id UUID DEFAULT uuid_generate_v4(),
    customer_vehicle_id UUID NOT NULL,
    address_id UUID NOT NULL,
    address_type address_type NOT NULL DEFAULT 'CUSTOMER',
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled BOOLEAN NOT NULL DEFAULT true,
    CONSTRAINT customer_vehicle_address_pkey PRIMARY KEY (customer_vehicle_address_id),
    CONSTRAINT customer_vehicle_id FOREIGN KEY (customer_vehicle_id)
        REFERENCES customer_vehicle (customer_vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT address_id FOREIGN KEY (address_id)
        REFERENCES address (address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT valid_address_type CHECK
        (address_type IN ('CUSTOMER', 'VEHICLE', 'DELIVERY', 'PICKUP'))
);