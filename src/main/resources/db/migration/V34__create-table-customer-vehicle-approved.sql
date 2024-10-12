-- Cria a tabela "customer_vehicle_approved" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer_vehicle_approved
(
    -- Identificador único da aprovação ou reprovação do veículo do cliente (obrigatório)
    customer_vehicle_approved_id UUID DEFAULT uuid_generate_v4(),

    -- Identificador único do veículo do cliente associado (obrigatório)
    customer_vehicle_id UUID NOT NULL, 

    -- Identificador do usuário que aprovou o cadastro
    approved_by UUID, 

    -- Identificador do usuário que reprovou o cadastro
    reproved_by UUID, 

    -- Mensagem com a justificativa em caso de reprovação
    message TEXT, 

    -- Identificador do usuário que criou o registro (obrigatório)
    created_by UUID NOT NULL, 

    -- Data de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, 

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, 

    -- Identificador do usuário que modificou o registro
    modified_by UUID, 

    -- Indicador de ativação do registro (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true, 

    -- Chave primária da tabela 'customer_vehicle_approved'
    CONSTRAINT customer_vehicle_approved_pkey PRIMARY KEY (customer_vehicle_approved_id),

    -- Chave estrangeira para a tabela customer_vehicle
    CONSTRAINT customer_vehicle_approved_to_customer_vehicle_fk FOREIGN KEY (customer_vehicle_id)
        REFERENCES customer_vehicle (customer_vehicle_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar aprovações e reprovações relacionadas aos veículos dos clientes
COMMENT ON TABLE customer_vehicle_approved IS 'Tabela para armazenar aprovações e reprovações relacionadas aos veículos dos clientes.';

-- Comentários para cada campo
COMMENT ON COLUMN customer_vehicle_approved.customer_vehicle_approved_id IS 'Identificador único da aprovação ou reprovação do veículo do cliente.';
COMMENT ON COLUMN customer_vehicle_approved.customer_vehicle_id IS 'Identificador único do veículo do cliente associado.';
COMMENT ON COLUMN customer_vehicle_approved.approved_by IS 'Identificador do usuário que aprovou o cadastro.';
COMMENT ON COLUMN customer_vehicle_approved.reproved_by IS 'Identificador do usuário que reprovou o cadastro.';
COMMENT ON COLUMN customer_vehicle_approved.message IS 'Mensagem com a justificativa em caso de reprovação.';
COMMENT ON COLUMN customer_vehicle_approved.created_by IS 'Identificador do usuário que criou o registro.';
COMMENT ON COLUMN customer_vehicle_approved.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN customer_vehicle_approved.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN customer_vehicle_approved.modified_by IS 'Identificador do usuário que modificou o registro.';
COMMENT ON COLUMN customer_vehicle_approved.enabled IS 'Indicador de ativação do registro.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT customer_vehicle_approved_pkey ON customer_vehicle_approved IS 'Chave primária da tabela customer_vehicle_approved, identificador único da aprovação ou reprovação.';
COMMENT ON CONSTRAINT customer_vehicle_approved_to_customer_vehicle_fk ON customer_vehicle_approved IS 'Chave estrangeira referenciando a tabela customer_vehicle para o veículo associado.';