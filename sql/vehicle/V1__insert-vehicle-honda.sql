SET search_path TO hurr;

INSERT INTO vehicle(vehicle_name, vehicle_brand_id)
VALUES
   ('ACCORD', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA')),
   ('CITY', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA')),
   ('CIVIC', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA')),
   ('CRV', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA')),
   ('FIT', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA')),
   ('HR-V', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA')),
   ('LEGEND', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA')),
   ('PRELUDE', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA')),
   ('WR-V', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA')),
   ('ZR-V', (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'HONDA'));