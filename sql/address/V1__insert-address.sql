SET search_path TO hurr;

INSERT INTO address(
	street_address, "number", country_id, state_id, city_id, address_type_id, zip_code, nickname)
	VALUES ('Rua Utinga', 37,
	(SELECT country_id FROM country WHERE country_name = 'Brasil'),
	(SELECT state_id FROM state WHERE state_name = 'São Paulo'),
	(SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'),
	(SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE'),
	'06851-020',
	'Rua Utinga');

INSERT INTO address(
	street_address, "number", country_id, state_id, city_id, address_type_id, zip_code, nickname)
	VALUES ('Av. Quinze de Novembro', 89,
	(SELECT country_id FROM country WHERE country_name = 'Brasil'),
	(SELECT state_id FROM state WHERE state_name = 'São Paulo'),
	(SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'),
	(SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE'),
	'06850-100',
	'Av. Quinze');

INSERT INTO address(
	street_address, "number", country_id, state_id, city_id, address_type_id, zip_code, nickname)
	VALUES ('Av. Quinze de Novembro', 662,
	(SELECT country_id FROM country WHERE country_name = 'Brasil'),
	(SELECT state_id FROM state WHERE state_name = 'São Paulo'),
	(SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'),
	(SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE'),
	'06850-100',
	'Av. Quinze');

INSERT INTO address(
	street_address, "number", country_id, state_id, city_id, address_type_id, zip_code, nickname)
	VALUES ('Av. Guacy Fernandes Domingues', 200,
	(SELECT country_id FROM country WHERE country_name = 'Brasil'),
	(SELECT state_id FROM state WHERE state_name = 'São Paulo'),
	(SELECT city_id FROM city WHERE city_name = 'Itapecerica da Serra'),
	(SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE'),
	'06854-100',
	'Av. Guacy');