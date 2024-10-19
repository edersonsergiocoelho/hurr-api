SET search_path TO hurr;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES 
  ('BANDEIRANTE', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
  ('CAMRY', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
  ('COROLLA', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
  ('COROLLA CROSS', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
  ('ETIOS', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
  ('GR COROLLA', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
  ('HILUX', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
  ('HILUX SW4', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
  ('PRIUS', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
  ('RAV4', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
  ('YARIS', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'))
ON CONFLICT DO NOTHING;