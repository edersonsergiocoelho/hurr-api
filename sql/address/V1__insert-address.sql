SET search_path TO hurr;

INSERT INTO address(
	street_address, "number", country_id, state_id, city_id, zip_code)
	VALUES ('Rua Utinga', 37,
	(SELECT country_id FROM country WHERE country_name = 'Brasil'),
	(SELECT state_id FROM state WHERE state_name = 'São Paulo'),
	(SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'), '06851-020');

INSERT INTO address(
	street_address, "number", country_id, state_id, city_id, zip_code)
	VALUES ('Av. Quinze de Novembro', 89,
	(SELECT country_id FROM country WHERE country_name = 'Brasil'),
	(SELECT state_id FROM state WHERE state_name = 'São Paulo'),
	(SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'), '06850-100');

INSERT INTO address(
	street_address, "number", country_id, state_id, city_id, zip_code)
	VALUES ('Av. Quinze de Novembro', 662,
	(SELECT country_id FROM country WHERE country_name = 'Brasil'),
	(SELECT state_id FROM state WHERE state_name = 'São Paulo'),
	(SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'), '06850-100');

INSERT INTO address(
	street_address, "number", country_id, state_id, city_id, zip_code)
	VALUES ('Av. Guacy Fernandes Domingues', 200,
	(SELECT country_id FROM country WHERE country_name = 'Brasil'),
	(SELECT state_id FROM state WHERE state_name = 'São Paulo'),
	(SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'), '06854-100');