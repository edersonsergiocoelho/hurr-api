SET search_path TO hurr;

INSERT INTO city(city_name, country_id, state_id)
	VALUES
	('Itapecerica da Serra',
	(SELECT country_id FROM country WHERE country_name = 'Brasil'),
	(SELECT state_id FROM state WHERE state_name = 'São Paulo'));