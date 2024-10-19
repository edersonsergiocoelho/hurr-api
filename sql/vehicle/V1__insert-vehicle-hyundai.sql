SET search_path TO hurr;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('HB20', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HYUNDAI'))
ON CONFLICT DO NOTHING;