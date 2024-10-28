-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "customer" se ela ainda não existir
CREATE TABLE IF NOT EXISTS customer
(
    -- Identificador único do cliente
    customer_id UUID DEFAULT uuid_generate_v4(),

    -- Nome do cliente
    first_name CHARACTER VARYING(100),

    -- Sobrenome do cliente
    last_name CHARACTER VARYING(100),

    -- Tipo de cliente (padrão é 'CUSTOMER')
    customer_type CHARACTER VARYING(50) NOT NULL DEFAULT 'CUSTOMER',

    -- Endereço de email do cliente (obrigatório)
    email CHARACTER VARYING(300) NOT NULL,

    -- Código de verificação do email
    email_verification_code CHARACTER VARYING(6),

    -- Indica se o email foi validado
    email_validated boolean NOT NULL DEFAULT false,

    -- DDI do telefone
    ddi_phone CHARACTER VARYING(10),

    -- Número de telefone do cliente
    phone CHARACTER VARYING(20),

    -- Código de verificação do telefone
    phone_verification_code CHARACTER VARYING(6),

    -- Indica se o telefone foi validado
    phone_validated boolean NOT NULL DEFAULT false,

    -- Data de nascimento do cliente
    date_of_birth DATE,

    -- CPF do cliente (Cadastro de Pessoas Físicas)
    cpf CHARACTER VARYING(20),

    -- Número de identidade do cliente
    identity_number CHARACTER VARYING(20),

    -- Órgão emissor da identidade
    identity_number_issuing_body CHARACTER VARYING(20),

    -- UF do órgão emissor da identidade
    identity_number_issuing_body_uf CHARACTER VARYING(10),

    -- Indica se o número de identidade foi validado
    identity_number_validated boolean NOT NULL DEFAULT false,

    -- Identificador do arquivo da identidade
    identity_number_file_id UUID,

    -- Número de registro da carteira de motorista
    driver_license_registration_number CHARACTER VARYING(20),

    -- Categoria da carteira de motorista
    driver_license_category CHARACTER VARYING(10),

    -- Data da primeira habilitação
    driver_license_first_license_date TIMESTAMP WITHOUT TIME ZONE,

    -- Data de expiração da carteira de motorista
    driver_license_expiration_date TIMESTAMP WITHOUT TIME ZONE,

    -- Data de emissão da carteira de motorista
    driver_license_issue_date TIMESTAMP WITHOUT TIME ZONE,

    -- UF de emissão da carteira de motorista
    driver_license_issue_uf CHARACTER VARYING(10),

    -- Indica se a carteira de motorista foi validada
    driver_license_validated boolean NOT NULL DEFAULT false,

    -- Identificador do arquivo da carteira de motorista
    driver_license_file_id UUID,

    -- Data de criação do registro
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o cliente está habilitado
    enabled boolean NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'customer'
    CONSTRAINT customer_pkey PRIMARY KEY (customer_id),

    -- Define a restrição de unicidade para o email
    CONSTRAINT customer_unique UNIQUE (email),

    -- Chave estrangeira para o arquivo da carteira de motorista
    CONSTRAINT customer_driver_license_to_file_fk FOREIGN KEY (driver_license_file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Chave estrangeira para o arquivo da identidade
    CONSTRAINT customer_identity_number_to_file_fk FOREIGN KEY (identity_number_file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre clientes
COMMENT ON TABLE customer IS 'Tabela que armazena informações sobre clientes.';

-- Comentários para cada campo
COMMENT ON COLUMN customer.customer_id IS 'Identificador único do cliente.';
COMMENT ON COLUMN customer.first_name IS 'Nome do cliente.';
COMMENT ON COLUMN customer.last_name IS 'Sobrenome do cliente.';
COMMENT ON COLUMN customer.customer_type IS 'Tipo de cliente (padrão é ''CUSTOMER'').';
COMMENT ON COLUMN customer.email IS 'Endereço de email do cliente.';
COMMENT ON COLUMN customer.email_verification_code IS 'Código de verificação do email.';
COMMENT ON COLUMN customer.email_validated IS 'Indica se o email foi validado.';
COMMENT ON COLUMN customer.ddi_phone IS 'DDI do telefone.';
COMMENT ON COLUMN customer.phone IS 'Número de telefone do cliente.';
COMMENT ON COLUMN customer.phone_verification_code IS 'Código de verificação do telefone.';
COMMENT ON COLUMN customer.phone_validated IS 'Indica se o telefone foi validado.';
COMMENT ON COLUMN customer.date_of_birth IS 'Data de nascimento do cliente.';
COMMENT ON COLUMN customer.cpf IS 'CPF do cliente.';
COMMENT ON COLUMN customer.identity_number IS 'Número de identidade do cliente.';
COMMENT ON COLUMN customer.identity_number_issuing_body IS 'Órgão emissor da identidade.';
COMMENT ON COLUMN customer.identity_number_issuing_body_uf IS 'UF do órgão emissor da identidade.';
COMMENT ON COLUMN customer.identity_number_validated IS 'Indica se o número de identidade foi validado.';
COMMENT ON COLUMN customer.identity_number_file_id IS 'Identificador do arquivo da identidade.';
COMMENT ON COLUMN customer.driver_license_registration_number IS 'Número de registro da carteira de motorista.';
COMMENT ON COLUMN customer.driver_license_category IS 'Categoria da carteira de motorista.';
COMMENT ON COLUMN customer.driver_license_first_license_date IS 'Data da primeira habilitação.';
COMMENT ON COLUMN customer.driver_license_expiration_date IS 'Data de expiração da carteira de motorista.';
COMMENT ON COLUMN customer.driver_license_issue_date IS 'Data de emissão da carteira de motorista.';
COMMENT ON COLUMN customer.driver_license_issue_uf IS 'UF de emissão da carteira de motorista.';
COMMENT ON COLUMN customer.driver_license_validated IS 'Indica se a carteira de motorista foi validada.';
COMMENT ON COLUMN customer.driver_license_file_id IS 'Identificador do arquivo da carteira de motorista.';
COMMENT ON COLUMN customer.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN customer.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN customer.enabled IS 'Indica se o cliente está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT customer_pkey ON "customer" IS 'Chave primária da tabela customer, identificador único do cliente.';
COMMENT ON CONSTRAINT customer_unique ON "customer" IS 'Restrição de unicidade para o email do cliente.';
COMMENT ON CONSTRAINT customer_driver_license_to_file_fk ON "customer" IS 'Chave estrangeira que referencia a tabela file para o arquivo da carteira de motorista.';
COMMENT ON CONSTRAINT customer_identity_number_to_file_fk ON "customer" IS 'Chave estrangeira que referencia a tabela file para o arquivo da identidade.';