SET search_path TO hurr;

INSERT INTO vehicle_model (
    vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
    ('2.0 TSI 16V R-LINE',
     (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'FUSCA'),
     (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Hatchback'))
ON CONFLICT DO NOTHING;