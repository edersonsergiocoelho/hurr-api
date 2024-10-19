SET search_path TO hurr;

INSERT INTO payment_status (payment_status_name) VALUES ('PENDING') ON CONFLICT (payment_status_name) DO NOTHING;
INSERT INTO payment_status (payment_status_name) VALUES ('PAID') ON CONFLICT (payment_status_name) DO NOTHING;