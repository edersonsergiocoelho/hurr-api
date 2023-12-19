SET search_path TO hurr;

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('Bandeirante',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('Camry',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('Corolla',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('Corolla Cross',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('Etios',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('GR Corolla',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('Hilux',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('Hilux SW4',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('Prius',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('RAV4',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));

INSERT INTO vehicle(
  vehicle_name, vehicle_brand_id)
VALUES ('Yaris',
  (SELECT vehicle_brand_id FROM vehicle_brand WHERE vehicle_brand_name = 'Toyota'));