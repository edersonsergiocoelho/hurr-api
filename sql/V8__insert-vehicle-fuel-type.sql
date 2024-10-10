SET search_path TO hurr;

INSERT INTO vehicle_fuel_type (vehicle_fuel_type_name)
VALUES ('Gasolina')
ON CONFLICT (vehicle_fuel_type_name) DO NOTHING;

INSERT INTO vehicle_fuel_type (vehicle_fuel_type_name)
VALUES ('Álcool')
ON CONFLICT (vehicle_fuel_type_name) DO NOTHING;

INSERT INTO vehicle_fuel_type (vehicle_fuel_type_name)
VALUES ('Diesel')
ON CONFLICT (vehicle_fuel_type_name) DO NOTHING;

INSERT INTO vehicle_fuel_type (vehicle_fuel_type_name)
VALUES ('GNV')
ON CONFLICT (vehicle_fuel_type_name) DO NOTHING;

INSERT INTO vehicle_fuel_type (vehicle_fuel_type_name)
VALUES ('Elétrico')
ON CONFLICT (vehicle_fuel_type_name) DO NOTHING;

INSERT INTO vehicle_fuel_type (vehicle_fuel_type_name)
VALUES ('Híbrido (Gasolina/Elétrico)')
ON CONFLICT (vehicle_fuel_type_name) DO NOTHING;

INSERT INTO vehicle_fuel_type (vehicle_fuel_type_name)
VALUES ('Hidrogênio')
ON CONFLICT (vehicle_fuel_type_name) DO NOTHING;

INSERT INTO vehicle_fuel_type (vehicle_fuel_type_name)
VALUES ('Gás Natural Liquefeito (GNL)')
ON CONFLICT (vehicle_fuel_type_name) DO NOTHING;

INSERT INTO vehicle_fuel_type (vehicle_fuel_type_name)
VALUES ('Biocombustível')
ON CONFLICT (vehicle_fuel_type_name) DO NOTHING;

INSERT INTO vehicle_fuel_type (vehicle_fuel_type_name)
VALUES ('Outro')
ON CONFLICT (vehicle_fuel_type_name) DO NOTHING;