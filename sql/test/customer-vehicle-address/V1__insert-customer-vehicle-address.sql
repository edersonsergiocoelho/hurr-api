-- Define o caminho de busca para o esquema 'hurr'
SET search_path TO hurr;

-- Inserção para FUSCA
INSERT INTO customer_vehicle_address (
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     WHERE v.vehicle_name = 'FUSCA'),
    (SELECT address_id FROM address
     WHERE street_address = 'Av. das Flôres' AND "number" = 123
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

-- Inserção para CIVIC
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     JOIN vehicle_model vm ON cv.vehicle_model_id = vm.vehicle_model_id
     WHERE v.vehicle_name = 'CIVIC'
     AND vm.vehicle_model_name = '1.5 16V TURBO GASOLINA SI COUPÉ 2P MANUAL'),
    (SELECT address_id FROM address
     WHERE street_address = 'Avenida Paulista' AND "number" = 1000
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

---- Inserção para PALIO
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     JOIN vehicle_model vm ON cv.vehicle_model_id = vm.vehicle_model_id
     WHERE v.vehicle_name = 'PALIO'
     AND vm.vehicle_model_name = '1.0 Mpi Fire Ex 8v Gasolina 4p'),
    (SELECT address_id FROM address
     WHERE street_address = 'Rua da Lapa' AND "number" = 456
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

---- Inserção para HR-V
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     WHERE v.vehicle_name = 'HR-V'),
    (SELECT address_id FROM address
     WHERE street_address = 'Av. NIterói' AND "number" = 789
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

---- Inserção para ONIX
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     WHERE v.vehicle_name = 'ONIX'),
    (SELECT address_id FROM address
     WHERE street_address = 'Avenida Brigadeiro Faria Lima' AND "number" = 2000
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

---- Inserção para JETTA
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     WHERE v.vehicle_name = 'JETTA'),
    (SELECT address_id FROM address
     WHERE street_address = 'Rua das Palmeiras' AND "number" = 321
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

---- Inserção para COROLLA
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     WHERE v.vehicle_name = 'COROLLA'),
    (SELECT address_id FROM address
     WHERE street_address = 'Rua Augusta' AND "number" = 1100
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

---- Inserção para HB20
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     WHERE v.vehicle_name = 'HB20'),
    (SELECT address_id FROM address
     WHERE street_address = 'Rua Estados Unidos' AND "number" = 654
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

---- Inserção para S10
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     WHERE v.vehicle_name = 'S10'),
    (SELECT address_id FROM address
     WHERE street_address = 'Avenida dos Estados' AND "number" = 1500
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

---- Inserção para KICKS
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     WHERE v.vehicle_name = 'KICKS'),
    (SELECT address_id FROM address
     WHERE street_address = 'Rua Catende' AND "number" = 789
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

---- Inserção para PALIO (segunda ocorrência)
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     JOIN vehicle_model vm ON cv.vehicle_model_id = vm.vehicle_model_id
     WHERE v.vehicle_name = 'PALIO'
     AND vm.vehicle_model_name = '1.6 MPI EL 8V GASOLINA 4P'),
    (SELECT address_id FROM address
     WHERE street_address = 'Rua das Margaridas' AND "number" = 12
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;

---- Inserção para CIVIC (segunda ocorrência)
INSERT INTO customer_vehicle_address(
    customer_vehicle_id, address_id)
VALUES (
    (SELECT customer_vehicle_id FROM customer_vehicle cv
     JOIN vehicle v ON cv.vehicle_id = v.vehicle_id
     JOIN vehicle_model vm ON cv.vehicle_model_id = vm.vehicle_model_id
     WHERE v.vehicle_name = 'CIVIC'
     AND vm.vehicle_model_name = '2.0 16V FLEXONE EXL 4P'),
    (SELECT address_id FROM address
     WHERE street_address = 'Avenida Brigadeiro Faria Lima' AND "number" = 1500
     AND city_id = (SELECT city_id FROM city WHERE city_name = 'São Paulo')
     AND state_id = (SELECT state_id FROM state WHERE state_name = 'São Paulo')))
ON CONFLICT (customer_vehicle_id, address_id) 
DO NOTHING;