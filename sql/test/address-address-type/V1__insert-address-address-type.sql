-- Define o caminho de busca para o esquema 'hurr'
SET search_path TO hurr;

-- Inserção do tipo 'VEHICLE' para o endereço 'Av. das Flôres'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Av. das Flôres' AND "number" = 123
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Avenida Paulista'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Avenida Paulista' AND "number" = 1000
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Rua da Lapa'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Rua da Lapa' AND "number" = 456
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Av. NIterói'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Av. NIterói' AND "number" = 789
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Avenida Brigadeiro Faria Lima'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Avenida Brigadeiro Faria Lima' AND "number" = 2000
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Rua das Palmeiras'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Rua das Palmeiras' AND "number" = 321
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Rua Augusta'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Rua Augusta' AND "number" = 1100
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Rua Estados Unidos'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Rua Estados Unidos' AND "number" = 654
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Avenida dos Estados'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Avenida dos Estados' AND "number" = 1500
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Rua Catende'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Rua Catende' AND "number" = 789
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Rua das Margaridas'
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Rua das Margaridas' AND "number" = 12
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;

-- Inserção do tipo 'VEHICLE' para o endereço 'Avenida Brigadeiro Faria Lima' (segunda ocorrência)
INSERT INTO address_address_type (
    address_id, address_type_id
)
VALUES (
    (SELECT address_id FROM address 
     WHERE street_address = 'Avenida Brigadeiro Faria Lima' AND "number" = 1500
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE')
)
ON CONFLICT (address_id, address_type_id) 
DO NOTHING;