SET search_path TO hurr;

INSERT INTO customer_vehicle_review(customer_vehicle_id, customer_id, review, rating)
	VALUES ((SELECT customer_vehicle_id FROM customer_vehicle cv, vehicle v WHERE cv.vehicle_id = v.vehicle_id AND v.vehicle_name = 'ACCORD'),
			(SELECT customer_id FROM customer WHERE email = 'johndoe@example.com'),
			'O aluguel do Honda Accord foi uma experiência incrível! O conforto dos bancos de couro, juntamente com a potência do motor, proporcionou uma viagem suave e emocionante. A economia de combustível surpreendeu, permitindo uma ótima autonomia. No entanto, a conectividade Bluetooth poderia ter sido mais intuitiva. No geral, uma excelente escolha para quem busca estilo e desempenho em um sedan.',
			3);

INSERT INTO customer_vehicle_review(customer_vehicle_id, customer_id, review, rating)
	VALUES ((SELECT customer_vehicle_id FROM customer_vehicle cv, vehicle v WHERE cv.vehicle_id = v.vehicle_id AND v.vehicle_name = 'ACCORD'),
			(SELECT customer_id FROM customer WHERE email = 'kevintaylor@example.com'),
			'Minha experiência com o aluguel do Honda Accord foi excepcional! O design elegante combinado com a tecnologia avançada tornou a viagem mais agradável. O amplo espaço interno proporcionou conforto para todos os passageiros, enquanto o sistema de áudio premium proporcionou uma trilha sonora perfeita para a jornada. O único ponto de melhoria seria a sensibilidade dos sensores de estacionamento. No geral, uma ótima escolha para quem busca sofisticação e comodidade.',
			4);