SET search_path TO hurr;

INSERT INTO address_address_type
    (address_id, address_type_id)
    VALUES(
    (SELECT address_id FROM address WHERE street_address = 'Rua Utinga'),
    (SELECT address_type_id FROM address_type WHERE address_type_name = 'VEHICLE'));