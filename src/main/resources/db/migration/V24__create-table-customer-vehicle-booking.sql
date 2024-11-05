-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "customer_vehicle_booking" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer_vehicle_booking
(
    -- Identificador único da reserva do veículo do cliente
    customer_vehicle_booking_id UUID DEFAULT uuid_generate_v4(),

    -- Identificador do veículo do cliente (obrigatório)
    customer_vehicle_id UUID NOT NULL,

    -- Identificador do cliente (obrigatório)
    customer_id UUID NOT NULL,

    -- Identificador do endereço de cobrança do cliente (obrigatório)
    customer_address_billing_id UUID NOT NULL,

    -- Identificador do endereço de entrega do cliente
    customer_address_delivery_id UUID,

    -- Valor do endereço de entrega do cliente
    customer_address_delivery_value NUMERIC(13,2),

    -- Identificador do endereço de retirada do cliente
    customer_address_pickup_id UUID,

    -- Valor do endereço de retirada do cliente
    customer_address_pickup_value NUMERIC(13,2),

    -- Identificador da reserva (obrigatório)
    booking CHARACTER VARYING(100) NOT NULL,

    -- Data de início da reserva (obrigatório)
    reservation_start_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    -- Hora de início da reserva (obrigatório)
    reservation_start_time CHARACTER VARYING(5) NOT NULL,

    -- Data de término da reserva (obrigatório)
    reservation_end_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    -- Hora de término da reserva (obrigatório)
    reservation_end_time CHARACTER VARYING(5) NOT NULL,

    -- Número de dias da reserva
    days_reservation INTEGER NOT NULL,

    -- Taxa diária do veículo
    daily_rate NUMERIC(13,2) NOT NULL,

    -- Quilometragem de início da reserva
    booking_start_km NUMERIC,

    -- Quilometragem de término da reserva
    booking_end_km NUMERIC,

    -- Data de início da reserva
    booking_start_date TIMESTAMP WITHOUT TIME ZONE,

    -- Notas de check-in
    check_in_notes TEXT,

    -- Data de término da reserva
    booking_end_date TIMESTAMP WITHOUT TIME ZONE,

    -- Notas de check-out
    check_out_notes TEXT,

    -- Data de cancelamento da reserva
    booking_cancellation_date TIMESTAMP WITHOUT TIME ZONE,

    -- Valor da reserva retirável (obrigatório)
    withdrawable_booking_value NUMERIC(13,2) NOT NULL,

    -- Valor total da reserva (obrigatório)
    total_booking_value NUMERIC(13,2) NOT NULL,

    -- Valor total adicional da reserva
    total_additional_value NUMERIC(13,2) DEFAULT 0,

    -- Valor total final da reserva (obrigatório)
    total_final_booking_value NUMERIC(13,2) NOT NULL,

    -- Status da reserva (obrigatório)
    booking_status VARCHAR(50) NOT NULL DEFAULT 'RESERVED',

    -- Identificador do pagamento (obrigatório)
    mp_payment_id NUMERIC NOT NULL,

    -- Informações do pagamento em formato JSONB (obrigatório)
    mp_payment JSONB NOT NULL,

    -- Informações do reembolso do pagamento em formato JSONB
    mp_payment_refund JSONB,

    -- Identificador adicional do pagamento
    mp_payment_additional_id NUMERIC,

    -- Informações adicionais do pagamento em formato JSONB
    mp_payment_additional JSONB,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o registro está habilitado
    enabled BOOLEAN NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'customer_vehicle_booking'
    CONSTRAINT customer_vehicle_booking_pkey PRIMARY KEY (customer_vehicle_booking_id),

    -- Define a restrição de unicidade para a reserva
    CONSTRAINT customer_vehicle_booking_unique UNIQUE (booking),

    -- Define a chave estrangeira para o veículo do cliente associado
    CONSTRAINT customer_vehicle_booking_to_customer_vehicle_fk FOREIGN KEY (customer_vehicle_id)
        REFERENCES customer_vehicle (customer_vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o cliente associado
    CONSTRAINT customer_vehicle_booking_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o endereço de cobrança do cliente
    CONSTRAINT customer_address_billing_to_customer_address_fk FOREIGN KEY (customer_address_billing_id)
        REFERENCES customer_address (customer_address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o endereço de entrega do cliente
    CONSTRAINT customer_address_delivery_to_customer_address_fk FOREIGN KEY (customer_address_delivery_id)
        REFERENCES customer_address (customer_address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o endereço de retirada do cliente
    CONSTRAINT customer_address_pickup_to_customer_address_fk FOREIGN KEY (customer_address_pickup_id)
        REFERENCES customer_address (customer_address_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre as reservas de veículos dos clientes
COMMENT ON TABLE customer_vehicle_booking IS 'Tabela que armazena informações sobre reservas de veículos dos clientes.';

-- Comentários para cada campo
COMMENT ON COLUMN customer_vehicle_booking.customer_vehicle_booking_id IS 'Identificador único da reserva do veículo do cliente.';
COMMENT ON COLUMN customer_vehicle_booking.customer_vehicle_id IS 'Identificador do veículo do cliente associado.';
COMMENT ON COLUMN customer_vehicle_booking.customer_id IS 'Identificador do cliente associado.';
COMMENT ON COLUMN customer_vehicle_booking.customer_address_billing_id IS 'Identificador do endereço de cobrança do cliente.';
COMMENT ON COLUMN customer_vehicle_booking.customer_address_delivery_id IS 'Identificador do endereço de entrega do cliente.';
COMMENT ON COLUMN customer_vehicle_booking.customer_address_delivery_value IS 'Valor do endereço de entrega do cliente.';
COMMENT ON COLUMN customer_vehicle_booking.customer_address_pickup_id IS 'Identificador do endereço de retirada do cliente.';
COMMENT ON COLUMN customer_vehicle_booking.customer_address_pickup_value IS 'Valor do endereço de retirada do cliente.';
COMMENT ON COLUMN customer_vehicle_booking.booking IS 'Identificador da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.reservation_start_date IS 'Data de início da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.reservation_start_time IS 'Hora de início da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.reservation_end_date IS 'Data de término da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.reservation_end_time IS 'Hora de término da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.days_reservation IS 'Número total de dias da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.daily_rate IS 'Taxa diária do veículo.';
COMMENT ON COLUMN customer_vehicle_booking.booking_start_km IS 'Quilometragem de início da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.booking_end_km IS 'Quilometragem de término da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.booking_start_date IS 'Data de início da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.check_in_notes IS 'Notas de check-in.';
COMMENT ON COLUMN customer_vehicle_booking.booking_end_date IS 'Data de término da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.check_out_notes IS 'Notas de check-out.';
COMMENT ON COLUMN customer_vehicle_booking.booking_cancellation_date IS 'Data de cancelamento da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.withdrawable_booking_value IS 'Valor da reserva retirável.';
COMMENT ON COLUMN customer_vehicle_booking.total_booking_value IS 'Valor total da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.total_additional_value IS 'Valor total adicional da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.total_final_booking_value IS 'Valor total final da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.booking_status IS 'Status da reserva.';
COMMENT ON COLUMN customer_vehicle_booking.mp_payment_id IS 'Identificador do pagamento.';
COMMENT ON COLUMN customer_vehicle_booking.mp_payment IS 'Informações do pagamento.';
COMMENT ON COLUMN customer_vehicle_booking.mp_payment_refund IS 'Informações do reembolso do pagamento.';
COMMENT ON COLUMN customer_vehicle_booking.mp_payment_additional_id IS 'Identificador adicional do pagamento.';
COMMENT ON COLUMN customer_vehicle_booking.mp_payment_additional IS 'Informações adicionais do pagamento.';
COMMENT ON COLUMN customer_vehicle_booking.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN customer_vehicle_booking.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN customer_vehicle_booking.enabled IS 'Indica se o registro está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT customer_vehicle_booking_pkey ON customer_vehicle_booking IS 'Chave primária da tabela customer_vehicle_booking, identificador único da reserva do veículo do cliente.';
COMMENT ON CONSTRAINT customer_vehicle_booking_unique ON customer_vehicle_booking IS 'Restrição de unicidade para a reserva.';
COMMENT ON CONSTRAINT customer_vehicle_booking_to_customer_vehicle_fk ON customer_vehicle_booking IS 'Chave estrangeira referenciando a tabela customer_vehicle para o veículo do cliente associado.';
COMMENT ON CONSTRAINT customer_vehicle_booking_to_customer_fk ON customer_vehicle_booking IS 'Chave estrangeira referenciando a tabela customer para o cliente associado.';
COMMENT ON CONSTRAINT customer_address_billing_to_customer_address_fk ON customer_vehicle_booking IS 'Chave estrangeira referenciando a tabela customer_address para o endereço de cobrança do cliente.';
COMMENT ON CONSTRAINT customer_address_delivery_to_customer_address_fk ON customer_vehicle_booking IS 'Chave estrangeira referenciando a tabela customer_address para o endereço de entrega do cliente.';
COMMENT ON CONSTRAINT customer_address_pickup_to_customer_address_fk ON customer_vehicle_booking IS 'Chave estrangeira referenciando a tabela customer_address para o endereço de retirada do cliente.';