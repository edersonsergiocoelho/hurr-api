SET search_path TO hurr;

INSERT INTO vehicle_transmission (vehicle_transmission_name)
VALUES ('Automática')
ON CONFLICT (vehicle_transmission_name) DO NOTHING;

INSERT INTO vehicle_transmission (vehicle_transmission_name)
VALUES ('Automática Sequencial')
ON CONFLICT (vehicle_transmission_name) DO NOTHING;

INSERT INTO vehicle_transmission (vehicle_transmission_name)
VALUES ('Automatizada')
ON CONFLICT (vehicle_transmission_name) DO NOTHING;

INSERT INTO vehicle_transmission (vehicle_transmission_name)
VALUES ('Automatizada DCT')
ON CONFLICT (vehicle_transmission_name) DO NOTHING;

INSERT INTO vehicle_transmission (vehicle_transmission_name)
VALUES ('CVT')
ON CONFLICT (vehicle_transmission_name) DO NOTHING;

INSERT INTO vehicle_transmission (vehicle_transmission_name)
VALUES ('Manual')
ON CONFLICT (vehicle_transmission_name) DO NOTHING;

INSERT INTO vehicle_transmission (vehicle_transmission_name)
VALUES ('Semi-Automática')
ON CONFLICT (vehicle_transmission_name) DO NOTHING;
