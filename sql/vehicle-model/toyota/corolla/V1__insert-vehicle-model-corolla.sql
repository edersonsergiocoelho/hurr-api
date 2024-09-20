SET search_path TO hurr;

INSERT INTO vehicle_model (
    vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
    ('Toyota Corolla 1.6 Dx 16v Gasolina 4p',
     (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA'),
     (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã'))
ON CONFLICT DO NOTHING;
