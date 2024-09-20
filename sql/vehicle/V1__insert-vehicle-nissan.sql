SET search_path TO hurr;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('KICKS', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'NISSAN'))
ON CONFLICT DO NOTHING;