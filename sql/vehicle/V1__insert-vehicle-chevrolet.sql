SET search_path TO hurr;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('ONIX', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'CHEVROLET'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('S10', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'CHEVROLET'))
ON CONFLICT DO NOTHING;