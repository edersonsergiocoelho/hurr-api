-- Define o caminho de busca para o esquema 'hurr'
SET search_path TO hurr;

-- Inserção dos estados brasileiros com tratamento para conflitos
INSERT INTO state (state_name, country_id)
VALUES
('Acre', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Alagoas', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Amapá', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Amazonas', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Bahia', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Ceará', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Distrito Federal', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Espírito Santo', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Goiás', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Maranhão', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Mato Grosso', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Mato Grosso do Sul', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Minas Gerais', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Pará', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Paraíba', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Paraná', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Pernambuco', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Piauí', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Rio de Janeiro', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Rio Grande do Norte', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Rio Grande do Sul', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Rondônia', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Roraima', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Santa Catarina', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('São Paulo', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Sergipe', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;

INSERT INTO state (state_name, country_id)
VALUES
('Tocantins', (SELECT country_id FROM country WHERE country_name = 'Brasil'))
ON CONFLICT (state_name, country_id) DO NOTHING;