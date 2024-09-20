SET search_path TO hurr;

INSERT INTO vehicle_model (
    vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
    ('1.0 Mpi Fire Ex 8v Gasolina 4p',
     (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'PALIO'),
     (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Hatchback'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle_model (
    vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
    ('1.6 MPI EL 8V GASOLINA 4P',
     (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'PALIO'),
     (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Hatchback'))
ON CONFLICT DO NOTHING;