-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Validação Do Cliente', 'Validação Do Cliente', 'pi pi-verified', 'customer/customer-validation', 1)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Torne-Se Um Parceiro', 'Torne-Se Um Parceiro', 'pi pi-shop', 'user-role/edit/role', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome