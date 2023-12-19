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
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled BOOLEAN NOT NULL DEFAULT true
);