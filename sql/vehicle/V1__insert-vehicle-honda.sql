SET search_path TO hurr;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('ACCORD', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('CITY', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('CIVIC', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('CRV', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('FIT', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('HR-V', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('LEGEND', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('PRELUDE', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('WR-V', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('ZR-V', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'))
ON CONFLICT DO NOTHING;