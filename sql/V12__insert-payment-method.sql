SET search_path TO hurr;

INSERT INTO payment_method (payment_method_name) VALUES ('PIX') ON CONFLICT (payment_method_name) DO NOTHING;