-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Inserir 'MENU_HEADER' com ON CONFLICT
INSERT INTO type_menu (type_menu_name, description)
VALUES ('MENU_HEADER', 'Menu de cabeçalhos')
ON CONFLICT (type_menu_name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Inserir 'MENU_HEADER_ICON' com ON CONFLICT
INSERT INTO type_menu (type_menu_name, description)
VALUES ('MENU_HEADER_ICON', 'Menu de ícones de cabeçalhos')
ON CONFLICT (type_menu_name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Inserir 'MENU_HEADER_DROPDOWN' com ON CONFLICT
INSERT INTO type_menu (type_menu_name, description)
VALUES ('MENU_HEADER_DROPDOWN', 'Menu suspenso de cabeçalhos')
ON CONFLICT (type_menu_name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Inserir 'MENU_SIDE_CUSTOMER_VEHICLE_EDIT' com ON CONFLICT
INSERT INTO type_menu (type_menu_name, description)
VALUES ('MENU_SIDE_CUSTOMER_VEHICLE_EDIT', 'Menu lateral para edição de veículos de clientes')
ON CONFLICT (type_menu_name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome