SET search_path TO hurr;

INSERT INTO vehicle_model(
    vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
('1.5 16V TURBO GASOLINA SI COUPÉ 2P MANUAL', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'CIVIC'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle_model(
    vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
('2.0 16V FLEXONE EXL 4P', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'CIVIC'), (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã'))
ON CONFLICT DO NOTHING;

