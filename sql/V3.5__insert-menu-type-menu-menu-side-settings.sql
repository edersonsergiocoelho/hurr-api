-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Configurações - Profile
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Profile', 'Perfil Do Usuário', 'pi pi-user', 'settings/profile', 1)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome

-- Configurações - Preference
INSERT INTO menu (name, description, icon, url, menu_order)
VALUES
('Preference', 'Preferências Do Usuário', 'pi pi-language', 'settings/preference', 1)
ON CONFLICT (name) DO NOTHING; -- Ignora a inserção em caso de conflito pelo nome