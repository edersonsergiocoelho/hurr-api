SET search_path TO hurr;

INSERT INTO vehicle_model (
    vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
    ('1.4 250 TSI TOTAL FLEX R-LINE TIPTRONIC',
     (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'JETTA'),
     (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Sedã'))
ON CONFLICT DO NOTHING;