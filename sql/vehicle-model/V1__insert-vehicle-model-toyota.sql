SET search_path TO hurr;

INSERT INTO vehicle_model(
    vehicle_model_name, vehicle_id, vehicle_categories_id)
	VALUES ('Toyota Corolla 1.6 Dx 16v Gasolina 4p', (SELECT vehicle_id FROM vehicle WHERE vehicle_name = 'Corolla'), (SELECT vehicle_categories_id FROM vehicle_categories WHERE vehicle_categories_name = 'Sedã'));