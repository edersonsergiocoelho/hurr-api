CREATE TABLE IF NOT EXISTS customer_vehicle_approved
(
    customer_vehicle_approved_id UUID DEFAULT uuid_generate_v4(), -- Identificador único da aprovação ou reprovação do veículo do cliente
    customer_vehicle_id UUID NOT NULL, -- Identificador único do veículo do cliente associado
    approved_by UUID, -- Identificador do usuário que aprovou o cadastro
    reproved_by UUID, -- Identificador do usuário que reprovou o cadastro
    message TEXT, -- Mensagem com a justificativa em caso de reprovação
    created_by UUID NOT NULL, -- Identificador do usuário que criou o registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data de criação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data de modificação do registro
    modified_by UUID, -- Identificador do usuário que modificou o registro
    enabled boolean NOT NULL DEFAULT true, -- Indicador de ativação do registro
    CONSTRAINT customer_vehicle_approved_pkey PRIMARY KEY (customer_vehicle_approved_id),
    CONSTRAINT customer_vehicle_approved_to_customer_vehicle_fk FOREIGN KEY (customer_vehicle_id)
        REFERENCES customer_vehicle (customer_vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE customer_vehicle_approved IS 'Tabela para armazenar aprovações e reprovações relacionadas aos veículos dos clientes';

COMMENT ON COLUMN customer_vehicle_approved.customer_vehicle_approved_id IS 'Identificador único da aprovação ou reprovação do veículo do cliente';
COMMENT ON COLUMN customer_vehicle_approved.customer_vehicle_id IS 'Identificador único do veículo do cliente associado';
COMMENT ON COLUMN customer_vehicle_approved.approved_by IS 'Identificador do usuário que aprovou o cadastro';
COMMENT ON COLUMN customer_vehicle_approved.reproved_by IS 'Identificador do usuário que reprovou o cadastro';
COMMENT ON COLUMN customer_vehicle_approved.message IS 'Mensagem com a justificativa em caso de reprovação';
COMMENT ON COLUMN customer_vehicle_approved.created_by IS 'Identificador do usuário que criou o registro';
COMMENT ON COLUMN customer_vehicle_approved.created_date IS 'Data de criação do registro';
COMMENT ON COLUMN customer_vehicle_approved.modified_date IS 'Data de modificação do registro';
COMMENT ON COLUMN customer_vehicle_approved.modified_by IS 'Identificador do usuário que modificou o registro';
COMMENT ON COLUMN customer_vehicle_approved.enabled IS 'Indicador de ativação do registro';