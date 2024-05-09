SET search_path TO hurr;

INSERT INTO customer_vehicle_address(
	customer_vehicle_id, address_id)
VALUES ((SELECT customer_vehicle_id FROM customer_vehicle cv, vehicle v WHERE cv.vehicle_id = v.vehicle_id AND v.vehicle_name = 'ACCORD'),
	    (SELECT address_id FROM address WHERE street_address = 'Rua Utinga' AND number = 37));

INSERT INTO customer_vehicle_address(
	customer_vehicle_id, address_id)
VALUES ((SELECT customer_vehicle_id FROM customer_vehicle cv, vehicle v WHERE cv.vehicle_id = v.vehicle_id AND v.vehicle_name = 'COROLLA CROSS'),
	    (SELECT address_id FROM address WHERE street_address = 'Av. Quinze de Novembro' AND number = 89));

INSERT INTO customer_vehicle_address(
	customer_vehicle_id, address_id)
VALUES ((SELECT customer_vehicle_id FROM customer_vehicle cv, vehicle v WHERE cv.vehicle_id = v.vehicle_id AND v.vehicle_name = 'COROLLA'),
	    (SELECT address_id FROM address WHERE street_address = 'Av. Quinze de Novembro' AND number = 662));

INSERT INTO customer_vehicle_address(
	customer_vehicle_id, address_id)
VALUES ((SELECT customer_vehicle_id FROM customer_vehicle cv, vehicle v WHERE cv.vehicle_id = v.vehicle_id AND v.vehicle_name = 'CIVIC'),
	    (SELECT address_id FROM address WHERE street_address = 'Av. Guacy Fernandes Domingues' AND number = 200));