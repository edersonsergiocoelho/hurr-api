-- Cria a tabela "customer_vehicle_review" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer_vehicle_review
(
    -- Identificador único da avaliação do veículo do cliente
    customer_vehicle_review_id UUID DEFAULT uuid_generate_v4(),

    -- Identificador da reserva do veículo do cliente (obrigatório)
    customer_vehicle_booking_id UUID NOT NULL,

    -- Identificador do cliente que fez a avaliação (obrigatório)
    customer_id UUID NOT NULL,

    -- Texto da avaliação (obrigatório)
    review TEXT NOT NULL,

    -- Avaliação em forma de nota (obrigatório)
    rating INT NOT NULL,

    -- Data de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o registro está habilitado (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'customer_vehicle_review'
    CONSTRAINT customer_vehicle_review_pkey PRIMARY KEY (customer_vehicle_review_id),

    -- Define a chave estrangeira para a reserva do veículo do cliente associado
    CONSTRAINT customer_vehicle_review_to_customer_vehicle_booking_fk FOREIGN KEY (customer_vehicle_booking_id)
        REFERENCES customer_vehicle_booking (customer_vehicle_booking_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o cliente que fez a avaliação
    CONSTRAINT customer_vehicle_review_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar avaliações de veículos dos clientes
COMMENT ON TABLE customer_vehicle_review IS 'Tabela que armazena avaliações de veículos feitas pelos clientes.';

-- Comentários para cada campo
COMMENT ON COLUMN customer_vehicle_review.customer_vehicle_review_id IS 'Identificador único da avaliação do veículo do cliente.';
COMMENT ON COLUMN customer_vehicle_review.customer_vehicle_booking_id IS 'Identificador da reserva do veículo do cliente associado.';
COMMENT ON COLUMN customer_vehicle_review.customer_id IS 'Identificador do cliente que fez a avaliação.';
COMMENT ON COLUMN customer_vehicle_review.review IS 'Texto da avaliação feita pelo cliente.';
COMMENT ON COLUMN customer_vehicle_review.rating IS 'Avaliação em forma de nota.';
COMMENT ON COLUMN customer_vehicle_review.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN customer_vehicle_review.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN customer_vehicle_review.enabled IS 'Indica se o registro está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT customer_vehicle_review_pkey ON customer_vehicle_review IS 'Chave primária da tabela customer_vehicle_review, identificador único da avaliação do veículo do cliente.';
COMMENT ON CONSTRAINT customer_vehicle_review_to_customer_vehicle_booking_fk ON customer_vehicle_review IS 'Chave estrangeira referenciando a tabela customer_vehicle_booking para a reserva do veículo do cliente associado.';
COMMENT ON CONSTRAINT customer_vehicle_review_to_customer_fk ON customer_vehicle_review IS 'Chave estrangeira referenciando a tabela customer para o cliente que fez a avaliação.';