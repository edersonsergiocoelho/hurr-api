-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- ROLE_ADMIN
-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_ADMIN'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Home'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_ADMIN'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Operações Administrador'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_HEADER') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_ADMIN'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Banco'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_ADMIN'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Taxas'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_ADMIN'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Método De Pagamento'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_ADMIN'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Status De Pagamento'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito

-- Inserir uma associação básica na tabela 'role_menu' usando subconsultas para buscar os UUIDs
INSERT INTO role_menu (role_id, menu_id, type_menu_id)
VALUES (
    (SELECT role_id FROM role WHERE role_name = 'ROLE_ADMIN'), -- Subconsulta para buscar o role_id
    (SELECT menu_id FROM menu WHERE name = 'Marca De Veículo'), -- Subconsulta para buscar o menu_id
    (SELECT type_menu_id FROM type_menu WHERE type_menu_name = 'MENU_SIDE') -- Subconsulta para buscar o type_menu_id
)
ON CONFLICT (role_id, menu_id, type_menu_id) DO NOTHING; -- Ignora a inserção em caso de conflito