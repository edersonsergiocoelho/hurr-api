SET search_path TO hurr;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    description,
    license_plate,
    renavam,
    renavam_state_id,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'joao.silva@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'FUSCA'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '2.0 TSI 16V R-LINE'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Azul'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Manual'), -- vehicle_transmission_id
    'Fusca 2014 com pintura azul brilhante e interior confortável. Ideal para quem gosta de carros clássicos.', -- description
    'FUS-2014', -- license_plate
    '20142014202', -- renavam
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'FUSC123456789', -- chassis
    2020, -- year_of_manufacture
    2020, -- year_of_the_car
    80000.00, -- vehicle_value
    50000, -- mileage_created
    80.00, -- daily_rate
    40.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'VEHICLE001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    description,
    license_plate,
    renavam,
    renavam_state_id,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'joao.silva@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'CIVIC'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '1.5 16V TURBO GASOLINA SI COUPÉ 2P MANUAL'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Preto'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Automática'), -- vehicle_transmission_id
    'Honda Civic 2021 com motor potente e tecnologia avançada. Ótima opção para quem busca conforto e performance.', -- description
    'CIV-2021', -- license_plate
    '20212021201', -- renavam
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'CIVC123456789', -- chassis
    2021, -- year_of_manufacture
    2021, -- year_of_the_car
    120000.00, -- vehicle_value
    35000, -- mileage_created
    120.00, -- daily_rate
    60.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'CIVIC001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    description,
    license_plate,
    renavam,
    renavam_state_id,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'joao.silva@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'PALIO'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '1.0 Mpi Fire Ex 8v Gasolina 4p'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Verde'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Manual'), -- vehicle_transmission_id
    'Fiat Palio 2001 com um design simples e eficiente. Ideal para quem busca um carro econômico e confiável.', -- description
    'PAL-2001', -- license_plate
    '20012018333', -- renavam
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'PAL123456789', -- chassis
    2018, -- year_of_manufacture
    2018, -- year_of_the_car
    25000.00, -- vehicle_value
    85000, -- mileage_created
    70.00, -- daily_rate
    35.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'PALIO001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    description,
    license_plate,
    renavam,
    renavam_state_id,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'joao.silva@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'HR-V'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '1.8 16V FLEX EX 4P'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Prata'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Automática'), -- vehicle_transmission_id
    'Honda HR-V 2019 com ótimo espaço interno e tecnologia moderna. Perfeito para famílias e viagens.', -- description
    'HRV-2019', -- license_plate
    '20192019444', -- renavam
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'HRV123456789', -- chassis
    2019, -- year_of_manufacture
    2019, -- year_of_the_car
    135000.00, -- vehicle_value
    33000, -- mileage_created
    110.00, -- daily_rate
    55.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'HRV001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    description,
    license_plate,
    renavam,
    renavam_state_id,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'ana.costa@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'ONIX'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '1.0 TURBO FLEX PLUS PREMIER'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Branco'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Manual'), -- vehicle_transmission_id
    'Chevrolet Onix 2019 com interior moderno e tecnologia de ponta. Perfeito para uso urbano.', -- description
    'ONX-2019', -- license_plate
    '20192019202', -- renavam
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'ONIX123456789', -- chassis
    2019, -- year_of_manufacture
    2019, -- year_of_the_car
    93000.00, -- vehicle_value
    45000, -- mileage_created
    90.00, -- daily_rate
    45.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'ONIX001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    description,
    license_plate,
    renavam,
    renavam_state_id,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'ana.costa@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'JETTA'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '1.4 250 TSI TOTAL FLEX R-LINE TIPTRONIC'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Vermelho'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Automática'), -- vehicle_transmission_id
    'Volkswagen Jetta 2020 com design elegante e motor potente. Ideal para viagens e conforto.', -- description
    'JTA-2020', -- license_plate
    '20202020202', -- renavam
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'JET123456789', -- chassis
    2020, -- year_of_manufacture
    2020, -- year_of_the_car
    140000.00, -- vehicle_value
    55000, -- mileage_created
    110.00, -- daily_rate
    55.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'JTA001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    description,
    license_plate,
    renavam,
    renavam_state_id,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'fernando.lima@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'COROLLA'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = 'COROLLA CROSS 2.0 VVT-IE FLEX XR DIRECT SHIFT'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Cinza'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Automática'), -- vehicle_transmission_id
    'Toyota Corolla 2021 com tecnologia avançada e conforto superior. Perfeito para viagens longas.', -- description
    'COR-2021', -- license_plate
    '20212021212', -- renavam
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'COR123456789', -- chassis
    2021, -- year_of_manufacture
    2021, -- year_of_the_car
    135000.00, -- vehicle_value
    35000, -- mileage_created
    130.00, -- daily_rate
    65.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'COR001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    description,
    license_plate,
    renavam,
    renavam_state_id,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'fernando.lima@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'HB20'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '1.6 16V FLEX VISION'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Prata'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Manual'), -- vehicle_transmission_id
    'Hyundai HB20 2018 com um design moderno e espaço interno confortável. Ideal para o dia a dia.', -- description
    'HB2-2018', -- license_plate
    '20182018202', -- renavam
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'HB20123456789', -- chassis
    2018, -- year_of_manufacture
    2018, -- year_of_the_car
    50000.00, -- vehicle_value
    45000, -- mileage_created
    85.00, -- daily_rate
    42.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'HB2001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    renavam_state_id,
    description,
    license_plate,
    renavam,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'fernando.lima@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'S10'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '2.8 16V TURBO DIESEL HIGH COUNTRY CD 4X4'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Preto'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Diesel'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Automática'), -- vehicle_transmission_id
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'Chevrolet S10 2021 com robustez e conforto para o trabalho e lazer. Ideal para quem precisa de uma picape forte.', -- description
    'S10-2021', -- license_plate
    '20212021234', -- renavam
    'S10123456789', -- chassis
    2021, -- year_of_manufacture
    2021, -- year_of_the_car
    55000.00, -- vehicle_value
    55000, -- mileage_created
    150.00, -- daily_rate
    75.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'S1001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    renavam_state_id,
    description,
    license_plate,
    renavam,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'fernando.lima@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'KICKS'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '1.6 16V FLEXSTART SPECIAL EDITION 4P XTRONIC'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Cinza'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Automática'), -- vehicle_transmission_id
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'Nissan Kicks 2020 com design moderno e tecnologia de ponta. Perfeito para quem busca um SUV compacto.', -- description
    'KCK-2020', -- license_plate
    '20202020234', -- renavam
    'KICK123456789', -- chassis
    2020, -- year_of_manufacture
    2020, -- year_of_the_car
    65000.00, -- vehicle_value
    65000, -- mileage_created
    120.00, -- daily_rate
    60.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'KCK001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    renavam_state_id,
    description,
    license_plate,
    renavam,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'fernando.lima@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'PALIO'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '1.6 MPI EL 8V GASOLINA 4P'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Vermelho'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Manual'), -- vehicle_transmission_id
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'Fiat Palio 2020 com um design moderno e confortável. Ideal para o uso diário.', -- description
    'PAL-2000', -- license_plate
    '20002000345', -- renavam
    'PAL123456788', -- chassis
    2020, -- year_of_manufacture
    2020, -- year_of_the_car
    25000.00, -- vehicle_value
    85000, -- mileage_created
    85.00, -- daily_rate
    42.00, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'PAL001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;

INSERT INTO customer_vehicle (
    customer_vehicle_id,
    customer_id,
    vehicle_id,
    vehicle_model_id,
    vehicle_color_id,
    vehicle_fuel_type_id,
    vehicle_transmission_id,
    renavam_state_id,
    description,
    license_plate,
    renavam,
    chassis,
    year_of_manufacture,
    year_of_the_car,
    vehicle_value,
    mileage_created,
    daily_rate,
    cleaning_fee,
    unlimited_mileage,
    limited_mileage,
    limited_mileage_included,
    limited_mileage_value,
    deliver_to_address,
    mileage_fee_delivery,
    pick_up_at_address,
    mileage_fee_pick_up,
    code,
    customer_vehicle_validated,
    advertisement_status,
    created_date,
    modified_date,
    enabled
)
VALUES (
    uuid_generate_v4(), -- customer_vehicle_id
    (SELECT customer_id FROM customer WHERE email = 'fernando.lima@example.com'), -- customer_id
    (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'CIVIC'), -- vehicle_id
    (SELECT vehicle_model_id FROM vehicle_model WHERE vehicle_model_name = '2.0 16V FLEXONE EXL 4P'), -- vehicle_model_id
    (SELECT vehicle_color_id FROM vehicle_color WHERE vehicle_color_name = 'Prata'), -- vehicle_color_id
    (SELECT vehicle_fuel_type_id FROM vehicle_fuel_type WHERE vehicle_fuel_type_name = 'Gasolina'), -- vehicle_fuel_type_id
    (SELECT vehicle_transmission_id FROM vehicle_transmission WHERE vehicle_transmission_name = 'Automática'), -- vehicle_transmission_id
    (SELECT state_id FROM state WHERE state_name = 'São Paulo'), -- renavam_state_id
    'Honda Civic 2019 com tecnologia e conforto superiores. Ideal para quem busca um sedan elegante.', -- description
    'CIV-2019', -- license_plate
    '20192020456', -- renavam
    'CIV123456788', -- chassis
    2019, -- year_of_manufacture
    2019, -- year_of_the_car
    95000.00, -- vehicle_value
    33000, -- mileage_created
    115.00, -- daily_rate
    57.50, -- cleaning_fee
    TRUE, -- unlimited_mileage
    FALSE, -- limited_mileage
    NULL, -- limited_mileage_included
    NULL, -- limited_mileage_value
    TRUE, -- deliver_to_address
    NULL, -- mileage_fee_delivery
    FALSE, -- pick_up_at_address
    NULL, -- mileage_fee_pick_up
    'CIV001', -- code
    FALSE, -- customer_vehicle_validated
    'DRAFT', -- advertisement_status
    current_timestamp, -- created_date
    NULL, -- modified_date
    TRUE -- enabled
) ON CONFLICT (chassis, renavam, license_plate, code) DO NOTHING;