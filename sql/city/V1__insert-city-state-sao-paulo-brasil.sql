-- Define o caminho de busca para o esquema 'hurr'
SET search_path TO hurr;

-- Inserção das cidades da Grande São Paulo
INSERT INTO city (city_name, state_id) 
VALUES 
    ('São Paulo', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Guarulhos', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('São Bernardo do Campo', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('São Caetano do Sul', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Osasco', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Diadema', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Mauá', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Suzano', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Ribeirão Pires', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Rio Grande da Serra', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Barueri', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Embu das Artes', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Embu-Guaçu', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

INSERT INTO city (city_name, state_id) 
VALUES 
    ('Cotia', 
        (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
    ON CONFLICT (city_name, state_id) DO NOTHING;

    INSERT INTO city (city_name, state_id)
    VALUES
        ('Itapecerica da Serra',
            (SELECT state_id FROM state WHERE state_name = 'São Paulo'))
        ON CONFLICT (city_name, state_id) DO NOTHING;