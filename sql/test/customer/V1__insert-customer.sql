SET search_path TO hurr;

-- João Silva
INSERT INTO customer (first_name, last_name, email, email_validated, phone_validated, identity_number_validated, driver_license_validated, enabled, created_date)
VALUES ('João', 'Silva', 'joao.silva@example.com', true, true, true, true, true, current_timestamp)
ON CONFLICT (email) DO NOTHING;

-- Maria Oliveira
INSERT INTO customer (first_name, last_name, email, email_validated, phone_validated, identity_number_validated, driver_license_validated, enabled, created_date)
VALUES ('Maria', 'Oliveira', 'maria.oliveira@example.com', true, true, true, true, true, current_timestamp)
ON CONFLICT (email) DO NOTHING;

-- Carlos Souza
INSERT INTO customer (first_name, last_name, email, email_validated, phone_validated, identity_number_validated, driver_license_validated, enabled, created_date)
VALUES ('Carlos', 'Souza', 'carlos.souza@example.com', true, true, true, true, true, current_timestamp)
ON CONFLICT (email) DO NOTHING;

-- Ana Costa
INSERT INTO customer (first_name, last_name, email, email_validated, phone_validated, identity_number_validated, driver_license_validated, enabled, created_date)
VALUES ('Ana', 'Costa', 'ana.costa@example.com', true, true, true, true, true, current_timestamp)
ON CONFLICT (email) DO NOTHING;

-- Luiz Pereira
INSERT INTO customer (first_name, last_name, email, email_validated, phone_validated, identity_number_validated, driver_license_validated, enabled, created_date)
VALUES ('Luiz', 'Pereira', 'luiz.pereira@example.com', true, true, true, true, true, current_timestamp)
ON CONFLICT (email) DO NOTHING;

-- Juliana Santos
INSERT INTO customer (first_name, last_name, email, email_validated, phone_validated, identity_number_validated, driver_license_validated, enabled, created_date)
VALUES ('Juliana', 'Santos', 'juliana.santos@example.com', true, true, true, true, true, current_timestamp)
ON CONFLICT (email) DO NOTHING;

-- Fernando Lima
INSERT INTO customer (first_name, last_name, email, email_validated, phone_validated, identity_number_validated, driver_license_validated, enabled, created_date)
VALUES ('Fernando', 'Lima', 'fernando.lima@example.com', true, true, true, true, true, current_timestamp)
ON CONFLICT (email) DO NOTHING;

-- Gabriela Rocha
INSERT INTO customer (first_name, last_name, email, email_validated, phone_validated, identity_number_validated, driver_license_validated, enabled, created_date)
VALUES ('Gabriela', 'Rocha', 'gabriela.rocha@example.com', true, true, true, true, true, current_timestamp)
ON CONFLICT (email) DO NOTHING;

-- Pedro Almeida
INSERT INTO customer (first_name, last_name, email, email_validated, phone_validated, identity_number_validated, driver_license_validated, enabled, created_date)
VALUES ('Pedro', 'Almeida', 'pedro.almeida@example.com', true, true, true, true, true, current_timestamp)
ON CONFLICT (email) DO NOTHING;

-- Renata Mendes (sem os campos "validated")
INSERT INTO customer (first_name, last_name, email, enabled, created_date)
VALUES ('Renata', 'Mendes', 'renata.mendes@example.com', true, current_timestamp)
ON CONFLICT (email) DO NOTHING;