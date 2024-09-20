SET search_path TO hurr;

INSERT INTO vehicle_model (
    vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
    ('1.6 16V FLEX VISION',
     (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'HB20'),
     (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Hatchback'))
ON CONFLICT DO NOTHING;