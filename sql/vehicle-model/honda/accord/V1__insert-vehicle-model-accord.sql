SET search_path TO hurr;

INSERT INTO vehicle_model (vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES 
('ACCORD 2.0 e:HEV ADVANCED E-CVT', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.0 e:HEV TOURING E-CVT', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.0 EX 16V GASOLINA 4P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.0 LX 16V GASOLINA 4P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.0 VTEC TURBO GASOLINA TOURING 10AT', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.2 EX 16V GASOLINA 4P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.2 LX 16V GASOLINA 4P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.2 LX 16V GASOLINA 4P MANUAL', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.3 EX 16V GASOLINA 4P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.3 EX COUPÉ 16V GASOLINA 2P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.3 EX-R 16V GASOLINA 4P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 2.4 EX 16V GASOLINA 4P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 3.0 EX Sedã V6 24V GASOLINA 4P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 3.0 EX V6 24V GASOLINA 4P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã')),
('ACCORD 3.5 EX V6 24V GASOLINA 4P AUTOMÁTICO', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã'));
