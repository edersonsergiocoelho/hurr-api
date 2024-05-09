SET search_path TO hurr;

INSERT INTO customer_vehicle (customer_id, vehicle_id, vehicle_model_id, vehicle_color_id, vehicle_fuel_type_id, vehicle_transmission_id, description, license_plate, renavam, chassis, year_of_manufacture, year_of_the_car, daily_rate, cleaning_fee, unlimited_mileage, limited_mileage, deliver_to_address, pick_up_at_address, created_date, modified_date, enabled)
VALUES ((SELECT customer_id FROM customer WHERE email = 'johndoe@example.com'),
		(SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ACCORD'),
		(SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = 'ACCORD 2.0 e:HEV ADVANCED E-CVT'),
		(SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Prata'),
		(SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Híbrido (Gasolina/Elétrico)'),
		(SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'CVT'),
		'O Honda Accord 2022 é um sedã sofisticado e moderno que oferece uma combinação elegante de design, desempenho avançado e tecnologia inovadora. Combinando estilo arrojado e um interior refinado, o Accord é uma escolha ideal para quem busca conforto, eficiência e tecnologia.',
		'ABC-1234', '12345678901', '12345678901234567', 2023, 2022, 100.00, 50.00, TRUE, FALSE, TRUE, FALSE, current_timestamp, NULL, TRUE);

INSERT INTO customer_vehicle (customer_id, vehicle_id, vehicle_model_id, vehicle_color_id, vehicle_fuel_type_id, vehicle_transmission_id, description,
                              license_plate, renavam, chassis, year_of_manufacture, year_of_the_car, daily_rate, cleaning_fee,
                              unlimited_mileage, limited_mileage, limited_mileage_included, limited_mileage_value,
                              deliver_to_address, mileage_fee_delivery, pick_up_at_address, mileage_fee_pick_up, created_date, modified_date, enabled)
VALUES ((SELECT customer_id FROM customer WHERE email = 'janedoe@example.com'),
		(SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA CROSS'),
		(SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = 'COROLLA CROSS 1.8 VVT-I HYBRID FLEX SPECIAL EDITION CVT'),
		(SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Branco'),
		(SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'),
		(SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Automática'),
		'Description 2',
		'DEF-5678', '98765432109', '98765432109876543', 2020, 2019, 120.00, 60.00, FALSE, TRUE, 500, 0.50, TRUE, 1, TRUE, 1, current_timestamp, NULL, TRUE);

INSERT INTO customer_vehicle (customer_id, vehicle_id, vehicle_model_id, vehicle_color_id, vehicle_fuel_type_id, vehicle_transmission_id, description, license_plate, renavam, chassis, year_of_manufacture, year_of_the_car, daily_rate, cleaning_fee, unlimited_mileage, limited_mileage, deliver_to_address, pick_up_at_address, created_date, modified_date, enabled)
VALUES ((SELECT customer_id FROM customer WHERE email = 'michaelsmith@example.com'),
		(SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA'),
		(SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = 'Toyota Corolla 1.6 Dx 16v Gasolina 4p'),
		(SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Cinza'),
		(SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'),
		(SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Automática'),
		'Description 3',
		'GHI-9012', '09876543210', '09876543210987654', 2018, 2017, 150.00, 70.00, TRUE, FALSE, TRUE, FALSE, current_timestamp, NULL, TRUE);

INSERT INTO customer_vehicle (customer_id, vehicle_id, vehicle_model_id, vehicle_color_id, vehicle_fuel_type_id, vehicle_transmission_id, description, license_plate, renavam, chassis, year_of_manufacture, year_of_the_car, daily_rate, cleaning_fee, unlimited_mileage, limited_mileage, deliver_to_address, pick_up_at_address, created_date, modified_date, enabled)
VALUES ((SELECT customer_id FROM customer WHERE email = 'mariagarcia@example.com'),
		(SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'CIVIC'),
		(SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '1.5 16V TURBO GASOLINA SI COUPÉ 2P MANUAL'),
		(SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Cinza'),
		(SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'),
		(SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Manual'),
		'Description 4',
		'GHI-9013', '09876543222', '09876543210987660', 2018, 2017, 150.00, 70.00, TRUE, FALSE, TRUE, FALSE, current_timestamp, NULL, TRUE);