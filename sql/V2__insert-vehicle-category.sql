SET search_path TO hurr;

INSERT INTO vehicle_category (vehicle_category_name)
VALUES ('Buggy')
ON CONFLICT (vehicle_category_name) DO NOTHING;

INSERT INTO vehicle_category (vehicle_category_name)
VALUES ('Conversível')
ON CONFLICT (vehicle_category_name) DO NOTHING;

INSERT INTO vehicle_category (vehicle_category_name)
VALUES ('Cupê')
ON CONFLICT (vehicle_category_name) DO NOTHING;

INSERT INTO vehicle_category (vehicle_category_name)
VALUES ('Hatchback')
ON CONFLICT (vehicle_category_name) DO NOTHING;

INSERT INTO vehicle_category (vehicle_category_name)
VALUES ('Minivan')
ON CONFLICT (vehicle_category_name) DO NOTHING;

INSERT INTO vehicle_category (vehicle_category_name)
VALUES ('Perua/SW')
ON CONFLICT (vehicle_category_name) DO NOTHING;

INSERT INTO vehicle_category (vehicle_category_name)
VALUES ('Picape')
ON CONFLICT (vehicle_category_name) DO NOTHING;

INSERT INTO vehicle_category (vehicle_category_name)
VALUES ('Sedã')
ON CONFLICT (vehicle_category_name) DO NOTHING;

INSERT INTO vehicle_category (vehicle_category_name)
VALUES ('Utilitário Esportivo')
ON CONFLICT (vehicle_category_name) DO NOTHING;

INSERT INTO vehicle_category (vehicle_category_name)
VALUES ('Van/Utilitário')
ON CONFLICT (vehicle_category_name) DO NOTHING;