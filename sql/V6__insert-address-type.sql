SET search_path TO hurr;

INSERT INTO address_type (address_type_name)
VALUES ('CUSTOMER')
ON CONFLICT (address_type_name) DO NOTHING;

INSERT INTO address_type (address_type_name)
VALUES ('VEHICLE')
ON CONFLICT (address_type_name) DO NOTHING;

INSERT INTO address_type (address_type_name)
VALUES ('DELIVERY')
ON CONFLICT (address_type_name) DO NOTHING;

INSERT INTO address_type (address_type_name)
VALUES ('PICKUP')
ON CONFLICT (address_type_name) DO NOTHING;
