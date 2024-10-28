-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "customer_vehicle_withdrawal_request" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer_vehicle_withdrawal_request
(
    -- Identificador único do pedido de retirada (obrigatório)
    customer_vehicle_withdrawal_request_id UUID DEFAULT uuid_generate_v4(),

    -- Identificador do cliente (obrigatório)
    customer_id UUID NOT NULL, 

    -- Identificador da reserva do veículo do cliente (obrigatório)
    customer_vehicle_booking_id UUID NOT NULL, 

    -- Identificador da conta bancária do cliente para retirada (obrigatório)
    customer_vehicle_bank_account UUID NOT NULL, 

    -- Identificador do método de pagamento (obrigatório)
    payment_method_id UUID NOT NULL, 

    -- Identificador do status do pagamento (obrigatório)
    payment_status_id UUID NOT NULL, 

    -- Data e hora da retirada (padrão: agora)
    withdrawal_date TIMESTAMP WITHOUT TIME ZONE DEFAULT current_timestamp, 

    -- Data e hora de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, 

    -- Data e hora da última modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, 

    -- Indica se o pedido de retirada está ativo (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true, 

    -- Chave primária da tabela 'customer_vehicle_withdrawal_request'
    CONSTRAINT customer_vehicle_withdrawal_request_pkey PRIMARY KEY (customer_vehicle_withdrawal_request_id),

    -- Chave estrangeira para a tabela customer
    CONSTRAINT customer_vehicle_withdrawal_request_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Chave estrangeira para a tabela customer_vehicle_booking
    CONSTRAINT customer_vehicle_withdrawal_request_to_customer_vehicle_booking_fk FOREIGN KEY (customer_vehicle_booking_id)
        REFERENCES customer_vehicle_booking (customer_vehicle_booking_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Chave estrangeira para a tabela customer_vehicle_bank_account
    CONSTRAINT customer_vehicle_withdrawal_request_to_customer_vehicle_bank_account_fk FOREIGN KEY (customer_vehicle_bank_account)
        REFERENCES customer_vehicle_bank_account (customer_vehicle_bank_account_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Chave estrangeira para a tabela payment_method
    CONSTRAINT customer_vehicle_withdrawal_request_to_payment_method_fk FOREIGN KEY (payment_method_id)
        REFERENCES payment_method (payment_method_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Chave estrangeira para a tabela payment_status
    CONSTRAINT customer_vehicle_withdrawal_request_to_payment_status_fk FOREIGN KEY (payment_status_id)
        REFERENCES payment_status (payment_status_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar pedidos de retirada de veículos dos clientes
COMMENT ON TABLE customer_vehicle_withdrawal_request IS 'Tabela que armazena pedidos de retirada de veículos dos clientes.';

-- Comentários para cada campo
COMMENT ON COLUMN customer_vehicle_withdrawal_request.customer_vehicle_withdrawal_request_id IS 'Identificador único do pedido de retirada.';
COMMENT ON COLUMN customer_vehicle_withdrawal_request.customer_id IS 'Identificador do cliente.';
COMMENT ON COLUMN customer_vehicle_withdrawal_request.customer_vehicle_booking_id IS 'Identificador da reserva do veículo do cliente.';
COMMENT ON COLUMN customer_vehicle_withdrawal_request.customer_vehicle_bank_account IS 'Identificador da conta bancária do cliente para retirada.';
COMMENT ON COLUMN customer_vehicle_withdrawal_request.payment_method_id IS 'Identificador do método de pagamento.';
COMMENT ON COLUMN customer_vehicle_withdrawal_request.payment_status_id IS 'Identificador do status do pagamento.';
COMMENT ON COLUMN customer_vehicle_withdrawal_request.withdrawal_date IS 'Data e hora da retirada.';
COMMENT ON COLUMN customer_vehicle_withdrawal_request.created_date IS 'Data e hora de criação do registro.';
COMMENT ON COLUMN customer_vehicle_withdrawal_request.modified_date IS 'Data e hora da última modificação do registro.';
COMMENT ON COLUMN customer_vehicle_withdrawal_request.enabled IS 'Indica se o pedido de retirada está ativo.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT customer_vehicle_withdrawal_request_pkey ON customer_vehicle_withdrawal_request IS 'Chave primária da tabela customer_vehicle_withdrawal_request, identificador único do pedido de retirada.';
COMMENT ON CONSTRAINT customer_vehicle_withdrawal_request_to_customer_fk ON customer_vehicle_withdrawal_request IS 'Chave estrangeira referenciando a tabela customer para o cliente associado.';
COMMENT ON CONSTRAINT customer_vehicle_withdrawal_request_to_customer_vehicle_booking_fk ON customer_vehicle_withdrawal_request IS 'Chave estrangeira referenciando a tabela customer_vehicle_booking para a reserva associada.';
COMMENT ON CONSTRAINT customer_vehicle_withdrawal_request_to_customer_vehicle_bank_account_fk ON customer_vehicle_withdrawal_request IS 'Chave estrangeira referenciando a tabela customer_vehicle_bank_account para a conta bancária associada.';
COMMENT ON CONSTRAINT customer_vehicle_withdrawal_request_to_payment_method_fk ON customer_vehicle_withdrawal_request IS 'Chave estrangeira referenciando a tabela payment_method para o método de pagamento associado.';
COMMENT ON CONSTRAINT customer_vehicle_withdrawal_request_to_payment_status_fk ON customer_vehicle_withdrawal_request IS 'Chave estrangeira referenciando a tabela payment_status para o status de pagamento associado.';