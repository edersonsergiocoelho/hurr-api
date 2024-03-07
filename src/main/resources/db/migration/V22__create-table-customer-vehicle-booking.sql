CREATE TABLE IF NOT EXISTS customer_vehicle_booking
(
    customer_vehicle_booking_id UUID DEFAULT uuid_generate_v4(),
    customer_vehicle_id UUID NOT NULL,
    customer_id UUID NOT NULL,
    booking CHARACTER VARYING(100) NOT NULL UNIQUE,
    booking_start_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    booking_end_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    booking_start_time CHARACTER VARYING(5) NOT NULL,
    booking_end_time CHARACTER VARYING(5) NOT NULL,
    total_booking_value NUMERIC(13,2) NOT NULL,
    mp_payment_id NUMERIC,
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
        ON DELETE NO ACTION
);