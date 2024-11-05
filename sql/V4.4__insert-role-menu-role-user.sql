-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- ROLE_USER
-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_USER'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Home'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_USER'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Minhas Reservas'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_USER'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Validação Do Cliente'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER_DROPDOWN') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_USER'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Torne-Se Um Parceiro'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER_DROPDOWN') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_USER'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Configurações'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER_DROPDOWN') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_USER'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Profile'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE_SETTINGS') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_USER'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Preference'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE_SETTINGS') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito