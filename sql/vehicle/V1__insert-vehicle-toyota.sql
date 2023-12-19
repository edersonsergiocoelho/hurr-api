SET search_path TO hurr;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES 
  ('BANDEIRANTE', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA')),
  ('CAMRY', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA')),
  ('COROLLA', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA')),
  ('COROLLA CROSS', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA')),
  ('ETIOS', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA')),
  ('GR COROLLA', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA')),
  ('HILUX', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA')),
  ('HILUX SW4', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA')),
  ('PRIUS', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA')),
  ('RAV4', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA')),
  ('YARIS', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'TOYOTA'));
