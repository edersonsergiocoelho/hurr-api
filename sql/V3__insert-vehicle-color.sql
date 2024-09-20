SET search_path TO hurr;

INSERT INTO vehicle_color (vehicle_color_name)
VALUES ('Preto') ON CONFLICT (vehicle_color_name) DO NOTHING;

INSERT INTO vehicle_color (vehicle_color_name)
VALUES ('Branco') ON CONFLICT (vehicle_color_name) DO NOTHING;

INSERT INTO vehicle_color (vehicle_color_name)
VALUES ('Prata') ON CONFLICT (vehicle_color_name) DO NOTHING;

INSERT INTO vehicle_color (vehicle_color_name)
VALUES ('Cinza') ON CONFLICT (vehicle_color_name) DO NOTHING;

INSERT INTO vehicle_color (vehicle_color_name)
VALUES ('Vermelho') ON CONFLICT (vehicle_color_name) DO NOTHING;

INSERT INTO vehicle_color (vehicle_color_name)
VALUES ('Azul') ON CONFLICT (vehicle_color_name) DO NOTHING;

INSERT INTO vehicle_color (vehicle_color_name)
VALUES ('Amarelo') ON CONFLICT (vehicle_color_name) DO NOTHING;

INSERT INTO vehicle_color (vehicle_color_name)
VALUES ('Verde') ON CONFLICT (vehicle_color_name) DO NOTHING;

INSERT INTO vehicle_color (vehicle_color_name)
VALUES ('Marrom') ON CONFLICT (vehicle_color_name) DO NOTHING;

INSERT INTO vehicle_color (vehicle_color_name)
VALUES ('Bege') ON CONFLICT (vehicle_color_name) DO NOTHING;