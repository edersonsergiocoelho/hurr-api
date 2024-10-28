-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "file_approved" se ela ainda não existir
CREATE TABLE IF NOT EXISTS file_approved
(
    -- Identificador único da aprovação do arquivo
    file_approved_id UUID DEFAULT uuid_generate_v4(),

    -- Identificador do arquivo aprovado (obrigatório)
    file_id UUID NOT NULL,

    -- Identificador do usuário que aprovou o arquivo
    approved_by UUID,

    -- Identificador do usuário que reprovou o arquivo
    reproved_by UUID,

    -- Mensagem associada à aprovação/reprovação do arquivo
    message TEXT,

    -- Nome da tabela do arquivo (obrigatório)
    file_table CHARACTER VARYING(100) NOT NULL,

    -- Identificador do cliente associado
    customer_id UUID,

    -- Identificador do usuário associado
    user_id UUID,

    -- Tipo de arquivo (obrigatório)
    file_type CHARACTER VARYING(100) NOT NULL,

    -- Identificador do usuário que criou o registro (obrigatório)
    created_by UUID NOT NULL,

    -- Data de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Identificador do usuário que modificou o registro
    modified_by UUID,

    -- Indica se o registro está habilitado (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true,

    -- Define a chave primária da tabela 'file_approved'
    CONSTRAINT file_approved_pkey PRIMARY KEY (file_approved_id),

    -- Define a chave estrangeira para o arquivo
    CONSTRAINT file_approved_to_file_fk FOREIGN KEY (file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o cliente
    CONSTRAINT file_approved_to_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    -- Define a chave estrangeira para o usuário
    CONSTRAINT file_approved_to_user_fk FOREIGN KEY (user_id)
        REFERENCES "user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela para armazenar informações sobre aprovações de arquivos
COMMENT ON TABLE file_approved IS 'Tabela que armazena informações sobre aprovações de arquivos.';

-- Comentários para cada campo
COMMENT ON COLUMN file_approved.file_approved_id IS 'Identificador único da aprovação do arquivo.';
COMMENT ON COLUMN file_approved.file_id IS 'Identificador do arquivo aprovado.';
COMMENT ON COLUMN file_approved.approved_by IS 'Identificador do usuário que aprovou o arquivo.';
COMMENT ON COLUMN file_approved.reproved_by IS 'Identificador do usuário que reprovou o arquivo.';
COMMENT ON COLUMN file_approved.message IS 'Mensagem associada à aprovação/reprovação do arquivo.';
COMMENT ON COLUMN file_approved.file_table IS 'Nome da tabela do arquivo.';
COMMENT ON COLUMN file_approved.customer_id IS 'Identificador do cliente associado.';
COMMENT ON COLUMN file_approved.user_id IS 'Identificador do usuário associado.';
COMMENT ON COLUMN file_approved.file_type IS 'Tipo de arquivo.';
COMMENT ON COLUMN file_approved.created_by IS 'Identificador do usuário que criou o registro.';
COMMENT ON COLUMN file_approved.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN file_approved.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN file_approved.modified_by IS 'Identificador do usuário que modificou o registro.';
COMMENT ON COLUMN file_approved.enabled IS 'Indica se o registro está habilitado.';

-- Comentários sobre as restrições
COMMENT ON CONSTRAINT file_approved_pkey ON file_approved IS 'Chave primária da tabela file_approved, identificador único da aprovação do arquivo.';
COMMENT ON CONSTRAINT file_approved_to_file_fk ON file_approved IS 'Chave estrangeira referenciando a tabela file para o arquivo aprovado.';
COMMENT ON CONSTRAINT file_approved_to_customer_fk ON file_approved IS 'Chave estrangeira referenciando a tabela customer para o cliente associado.';
COMMENT ON CONSTRAINT file_approved_to_user_fk ON file_approved IS 'Chave estrangeira referenciando a tabela user para o usuário associado.';