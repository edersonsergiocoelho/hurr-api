CREATE TABLE IF NOT EXISTS customer_withdrawal_request
(
    customer_withdrawal_request_id UUID DEFAULT uuid_generate_v4(), -- Identificador único do pedido de retirada
    customer_id UUID NOT NULL, -- Identificador do cliente
    customer_vehicle_booking_id UUID NOT NULL, -- Identificador da reserva do veículo do cliente
    customer_bank_account UUID NOT NULL, -- Identificador da conta bancária do cliente
    payment_method_id UUID NOT NULL, -- Identificador do método de pagamento usado para a retirada
    payment_status_id UUID NOT NULL, -- Identificador do status do pagamento
    withdrawal_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data e hora da retirada
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data e hora de criação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data e hora da última modificação do registro
    enabled BOOLEAN NOT NULL DEFAULT true, -- Indica se o pedido de retirada está ativo
    CONSTRAINT customer_withdrawal_request_pkey PRIMARY KEY (customer_withdrawal_request_id),
    CONSTRAINT customer_withdrawal_request_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_withdrawal_request_to_customer_vehicle_booking_fk FOREIGN KEY (customer_vehicle_booking_id)
        REFERENCES customer_vehicle_booking (customer_vehicle_booking_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_withdrawal_request_to_customer_bank_account_fk FOREIGN KEY (customer_bank_account)
        REFERENCES customer_bank_account (customer_bank_account_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_withdrawal_request_to_payment_method_fk FOREIGN KEY (payment_method_id)
        REFERENCES payment_method (payment_method_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT customer_withdrawal_request_to_payment_status_fk FOREIGN KEY (payment_status_id)
        REFERENCES payment_status (payment_status_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE customer_withdrawal_request IS 'Tabela que armazena os pedidos de retirada de clientes.';
COMMENT ON COLUMN customer_withdrawal_request.customer_withdrawal_request_id IS 'Identificador único do pedido de retirada';
COMMENT ON COLUMN customer_withdrawal_request.customer_id IS 'Identificador do cliente';
COMMENT ON COLUMN customer_withdrawal_request.customer_vehicle_booking_id IS 'Identificador da reserva do veículo do cliente';
COMMENT ON COLUMN customer_withdrawal_request.customer_bank_account IS 'Identificador da conta bancária do cliente';
COMMENT ON COLUMN customer_withdrawal_request.payment_method_id IS 'Identificador do método de pagamento usado para a retirada';
COMMENT ON COLUMN customer_withdrawal_request.payment_status_id IS 'Identificador do status do pagamento';
COMMENT ON COLUMN customer_withdrawal_request.withdrawal_date IS 'Data e hora da retirada';
COMMENT ON COLUMN customer_withdrawal_request.created_date IS 'Data e hora de criação do registro';
COMMENT ON COLUMN customer_withdrawal_request.modified_date IS 'Data e hora da última modificação do registro';
COMMENT ON COLUMN customer_withdrawal_request.enabled IS 'Indica se o pedido de retirada está ativo';