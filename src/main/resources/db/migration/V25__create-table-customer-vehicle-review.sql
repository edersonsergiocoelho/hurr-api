CREATE TABLE IF NOT EXISTS customer_vehicle_review
(
    customer_vehicle_review_id UUID DEFAULT uuid_generate_v4(),
    customer_vehicle_booking_id UUID NOT NULL,
    customer_id UUID NOT NULL,
    review TEXT NOT NULL,
    rating INT NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled BOOLEAN NOT NULL DEFAULT true,
    CONSTRAINT customer_vehicle_review_pkey PRIMARY KEY (customer_vehicle_review_id),
    CONSTRAINT customer_vehicle_review_to_customer_vehicle_booking_fk FOREIGN KEY (customer_vehicle_booking_id)
        REFERENCES customer_vehicle_booking (customer_vehicle_booking_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_vehicle_review_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);