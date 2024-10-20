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