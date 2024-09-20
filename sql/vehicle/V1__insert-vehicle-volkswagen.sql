SET search_path TO hurr;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('FUSCA', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'VOLKSWAGEN'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('JETTA', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'VOLKSWAGEN'))
ON CONFLICT DO NOTHING;