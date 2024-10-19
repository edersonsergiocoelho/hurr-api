-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Insere o papel 'ROLE_ADMIN' se não existir, ignora o conflito se o 'role_name' já estiver presente
INSERT INTO role (role_name) 
VALUES ('ROLE_ADMIN') 
ON CONFLICT (role_name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Insere o papel 'ROLE_CUSTOMER_VEHICLE' se não existir, ignora o conflito se o 'role_name' já estiver presente
INSERT INTO role (role_name) 
VALUES ('ROLE_CUSTOMER_VEHICLE') 
ON CONFLICT (role_name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Insere o papel 'ROLE_MODERATOR' se não existir, ignora o conflito se o 'role_name' já estiver presente
INSERT INTO role (role_name) 
VALUES ('ROLE_MODERATOR') 
ON CONFLICT (role_name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Insere o papel 'ROLE_USER' se não existir, ignora o conflito se o 'role_name' já estiver presente
INSERT INTO role (role_name) 
VALUES ('ROLE_USER') 
ON CONFLICT (role_name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome