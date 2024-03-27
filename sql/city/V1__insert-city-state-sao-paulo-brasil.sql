SET search_path TO hurr;

INSERT INTO city(city_name, state_id)
	VALUES
	('Itapecerica da Serra',
	(SELECT state_id FROM state WHERE state_name = 'São Paulo'));