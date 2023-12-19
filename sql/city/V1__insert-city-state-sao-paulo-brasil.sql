SET search_path TO hurr;

INSERT INTO state(state_name, country_id) VALUES ('São Paulo', (SELECT country_id FROM country WHERE country_name = 'Brasil'));