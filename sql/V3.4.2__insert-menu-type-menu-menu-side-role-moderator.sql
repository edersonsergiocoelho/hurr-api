-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Operações Moderador
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Operações Moderador', 'Operações Do Moderador', 'pi pi-cog', 'file-approved', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Operações Moderador
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Solicitações De Aprovação De Documentos', 'Solicitações De Aprovação De Documento Para Validar O Cliente', 'pi pi-book', 'file-approved', 1)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Operações Moderador
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Solicitações De Aprovação De Cadastro De Carros', 'Solicitações De Aprovação De Cadastro De Carros Do Dono Do Carro', 'pi pi-car', 'customer-vehicle-approved', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Operações Moderador
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Solicitações De Retirada De Dinheiro', 'Solicitações De Retirada De Dinheiro Do Dono Do Carro', 'pi pi-money-bill', 'customer-vehicle-withdrawal-request', 3)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome