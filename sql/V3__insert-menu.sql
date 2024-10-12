-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Home', 'Home', 'pi pi-home', 'home', 1)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Minhas Reservas', 'Minhas Reservas - Verifique Aqui As Suas Últimas Reservas', 'pi pi-book', 'customer-vehicle-booking', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Reservas Dos Meus Carros', 'Reservas Dos Meus Carros - Verifique Aqui As Suas Últimas Reservas Dos Meus Carros', 'pi pi-book', 'customer-vehicle-booking/customer-vehicle', 3)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Meus Carros', 'Meus Carros - Verifique Aqui Os Meus Carros Cadastrados', 'pi pi-car', 'customer-vehicle', 4)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Ganhos', 'Ganhos', 'pi pi-money-bill', 'earnings', 5)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Validação Do Cliente', 'Validação Do Cliente', 'pi pi-verified', 'customer/customer-validation', 1)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Fotos', 'Editar Fotos Do Carro', 'pi pi-images', 'customer-vehicle/edit/photos/', 1)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Detalhes', 'Editar Detalhes Do Carro', 'pi pi-car', 'customer-vehicle/edit/detail/', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Preços', 'Editar Preços E Descontos', 'pi pi-dollar', 'customer-vehicle/edit/price-discount/', 3)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Endereços', 'Editar Endereços Do Carro', 'pi pi-map-marker', 'customer-vehicle/edit/addresses/', 4)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Quilometragem Limitada?', 'Editar Informações Da Quilometragem', 'pi pi pi-gauge', 'customer-vehicle/edit/limited-mileage/', 5)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Status Do Anúncio', 'Editar Status Do Anúncio', 'pi pi-book', 'customer-vehicle/edit/advertisement-status/', 6)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Operações Moderador', 'Operações Do Moderador', 'pi pi-cog', 'file-approved', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Solicitações De Aprovação De Documentos', 'Solicitações De Aprovação De Documento Para Validar O Cliente', 'pi pi-book', 'file-approved', 1)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Solicitações De Aprovação De Cadastro De Carros', 'Solicitações De Aprovação De Cadastro De Carros Do Dono Do Carro', 'pi pi-car', 'customer-vehicle-approved', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Solicitações De Retirada De Dinheiro', 'Solicitações De Retirada De Dinheiro Do Dono Do Carro', 'pi pi-money-bill', 'customer-withdrawal-request', 3)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Operações Administrador', 'Operações Do Administrador', 'pi pi-cog', 'payment-status', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Método De Pagamento', 'Método De Pagamento De Retirada De Dinheiro Do Dono Do Carro', 'pi pi-credit-card', 'payment-method', 1)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Status De Pagamento', 'Status De Pagamento De Retirada De Dinheiro Do Dono Do Carro', 'pi pi-check', 'payment-status', 2)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome