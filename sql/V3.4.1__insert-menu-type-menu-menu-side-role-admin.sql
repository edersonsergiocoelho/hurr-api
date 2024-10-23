-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Operações Administrador
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Operações Administrador', 'Operações Do Administrador', 'pi pi-cog', 'payment-status', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Operações Administrador - Bank
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Banco', 'Cadastro De Bancos', 'pi pi-building-columns', 'bank', 1)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Operações Administrador - PaymentMethod
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Método De Pagamento', 'Método De Pagamento De Retirada De Dinheiro Do Dono Do Carro', 'pi pi-credit-card', 'payment-method', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Operações Administrador - PaymentStatus
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Status De Pagamento', 'Status De Pagamento De Retirada De Dinheiro Do Dono Do Carro', 'pi pi-check', 'payment-status', 3)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Operações Administrador - VehicleBrand
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Marca De Veículo', 'Cadastro De Marca De Veículo', 'pi pi-car', 'vehicle-brand', 4)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome