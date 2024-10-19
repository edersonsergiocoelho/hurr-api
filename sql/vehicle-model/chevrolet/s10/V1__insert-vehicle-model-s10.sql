SET search_path TO hurr;

INSERT INTO vehicle_model (
    vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
    ('2.8 16V TURBO DIESEL HIGH COUNTRY CD 4X4',
     (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'S10'),
     (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Picape'))
ON CONFLICT DO NOTHING;