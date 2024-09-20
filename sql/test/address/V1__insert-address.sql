-- Define o caminho de busca para o esquema 'hurr'
SET search_path TO hurr;

-- Inserção do endereço 'Av. das Flôres'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Av. das Flôres', 123, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '06855-810', 'Av. das Flôres', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Avenida Paulista'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Avenida Paulista', 1000, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'São Paulo'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '01310-000', 'Avenida Paulista', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Rua da Lapa'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Rua da Lapa', 456, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'São Paulo'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '05001-000', 'Rua da Lapa', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Av. NIterói'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Av. NIterói', 789, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '06850-200', 'Av. NIterói', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Avenida Brigadeiro Faria Lima'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Avenida Brigadeiro Faria Lima', 2000, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'São Paulo'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '01451-000', 'Avenida Brigadeiro Faria Lima', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Rua das Palmeiras'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Rua das Palmeiras', 321, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '06852-000', 'Rua das Palmeiras', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Rua Augusta'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Rua Augusta', 1100, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'São Paulo'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '01305-000', 'Rua Augusta', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Rua Estados Unidos'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Rua Estados Unidos', 654, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '06850-200', 'Rua Estados Unidos', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Avenida dos Estados'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Avenida dos Estados', 1500, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'São Paulo'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '03100-000', 'Avenida dos Estados', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Rua Catende'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Rua Catende', 789, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '06851-240', 'Rua Catende', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Rua das Margaridas'
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Rua das Margaridas', 12, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'São Paulo'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '01315-000', 'Rua das Margaridas', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;

-- Inserção do endereço 'Avenida Brigadeiro Faria Lima' (segunda ocorrência)
INSERT INTO address (
    street_address, "number", complement, country_id, city_id, state_id, zip_code, nickname, created_date, modified_date, enabled)
VALUES (
    'Avenida Brigadeiro Faria Lima', 1500, NULL,
    (SELECT country_id FROM country WHERE country_name = 'Brasil'),
    (SELECT city_id FROM city WHERE city_name = 'São Paulo'),
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'),
    '01452-000', 'Avenida Brigadeiro Faria Lima', current_timestamp, NULL, true)
ON CONFLICT (street_address, "number", city_id, state_id) 
DO NOTHING;