-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- ROLE_CUSTOMER_VEHICLE
-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Home'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Minhas Reservas'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Reservas Dos Meus Carros'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Meus Carros'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Ganhos'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Fotos'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE_CUSTOMER_VEHICLE_EDIT') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Detalhes'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE_CUSTOMER_VEHICLE_EDIT') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Preços'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE_CUSTOMER_VEHICLE_EDIT') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Endereços'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE_CUSTOMER_VEHICLE_EDIT') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Quilometragem Limitada?'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE_CUSTOMER_VEHICLE_EDIT') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_CUSTOMER_VEHICLE'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Status Do Anúncio'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE_CUSTOMER_VEHICLE_EDIT') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito