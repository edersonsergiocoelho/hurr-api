SET search_path TO hurr;

INSERT INTO vehicle_model (vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES 
('COROLLA CROSS 1.8 VVT-I HYBRID FLEX SPECIAL EDITION CVT',
 (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA CROSS'),
 (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Utilitário Esportivo'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle_model (vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
('COROLLA CROSS 1.8 VVT-I HYBRID FLEX XRV CVT',
 (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA CROSS'),
 (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Utilitário Esportivo'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle_model (vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
('COROLLA CROSS 1.8 VVT-I HYBRID FLEX XRX CVT',
 (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA CROSS'),
 (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Utilitário Esportivo'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle_model (vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
('COROLLA CROSS 2.0 VVT-IE FLEX GR-SPORT DIRECT SHIFT',
 (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA CROSS'),
 (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Utilitário Esportivo'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle_model (vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
('COROLLA CROSS 2.0 VVT-IE FLEX XR DIRECT SHIFT',
 (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA CROSS'),
 (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Utilitário Esportivo'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle_model (vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
('COROLLA CROSS 2.0 VVT-IE FLEX XRE DIRECT SHIFT',
 (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA CROSS'),
 (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Utilitário Esportivo'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle_model (vehicle_model_name, vehicle_id, vehicle_category_id)
VALUES
('COROLLA CROSS 2.0 VVT-IE FLEX XRX DIRECT SHIFT',
 (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA CROSS'),
 (SELECT vehicle_category_id FROM vehicle_category WHERE vehicle_category_name = 'Utilitário Esportivo'))
ON CONFLICT DO NOTHING;
