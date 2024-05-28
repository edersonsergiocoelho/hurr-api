CREATE TABLE IF NOT EXISTS customer_vehicle_booking
(
    customer_vehicle_booking_id UUID DEFAULT uuid_generate_v4(),
    customer_vehicle_id UUID NOT NULL,
    customer_id UUID NOT NULL,
    customer_address_delivery_id UUID,
    customer_address_delivery_value NUMERIC(13,2),
    customer_address_pickup_id UUID,
    customer_address_pickup_value NUMERIC(13,2),
    booking CHARACTER VARYING(100) NOT NULL UNIQUE,
    booking_start_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    booking_end_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    booking_start_time CHARACTER VARYING(5) NOT NULL,
    booking_end_time CHARACTER VARYING(5) NOT NULL,
    booking_start_km NUMERIC,
    booking_end_km NUMERIC,
    booking_delivery_date TIMESTAMP WITHOUT TIME ZONE,
    total_booking_value NUMERIC(13,2) NOT NULL,
    mp_payment_id NUMERIC NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled BOOLEAN NOT NULL DEFAULT true,
    CONSTRAINT customer_vehicle_booking_pkey PRIMARY KEY (customer_vehicle_booking_id),
    CONSTRAINT customer_vehicle_booking_to_customer_vehicle_fk FOREIGN KEY (customer_vehicle_id)
        REFERENCES customer_vehicle (customer_vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_vehicle_booking_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_address_delivery_to_customer_address_fk FOREIGN KEY (customer_address_delivery_id)
        REFERENCES customer_address (customer_address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_address_pickup_to_customer_address_fk FOREIGN KEY (customer_address_pickup_id)
        REFERENCES customer_address (customer_address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);