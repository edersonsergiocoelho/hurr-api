-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

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