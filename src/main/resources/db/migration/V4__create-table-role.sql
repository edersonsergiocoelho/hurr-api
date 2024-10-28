-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "role" se ela ainda não existir
CREATE TABLE IF NOT EXISTS role
(
    -- Identificador único do papel (role), gerado automaticamente como UUID
    role_id UUID DEFAULT uuid_generate_v4(),
    
    -- Nome do papel, que deve ser único
    role_name CHARACTER VARYING(100) NOT NULL,
    
    -- Data de criação do registro do papel
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    
    -- Data de modificação do registro do papel
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    
    -- Indica se o papel está habilitado/ativo
    enabled BOOLEAN NOT NULL DEFAULT true,
    
    -- Define a chave primária para a tabela 'role' baseada no campo 'role_id'
    CONSTRAINT role_pkey PRIMARY KEY (role_id),

    -- Restrições que garantem que o nome do papel seja único
    CONSTRAINT role_unique UNIQUE (role_name)
);

-- Comentários para a tabela e seus campos

-- Tabela que armazena informações sobre papéis (roles) no sistema
COMMENT ON TABLE role IS 'Tabela que armazena informações sobre papéis no sistema, incluindo nome, datas de criação/modificação e status de habilitação.';

-- Comentários para cada campo

COMMENT ON COLUMN role.role_id IS 'Identificador único do papel, gerado automaticamente como UUID.';
COMMENT ON COLUMN role.role_name IS 'Nome do papel, que deve ser único dentro do sistema.';
COMMENT ON COLUMN role.created_date IS 'Data de criação do registro do papel.';
COMMENT ON COLUMN role.modified_date IS 'Data de modificação do registro do papel.';
COMMENT ON COLUMN role.enabled IS 'Indica se o papel está habilitado/ativo.';

-- Comentários para as constraints
COMMENT ON CONSTRAINT role_pkey ON "role" IS 'Chave primária da tabela role, baseada no campo role_id.';
COMMENT ON CONSTRAINT role_unique ON "role" IS 'Restrições que garantem que o nome do papel (role_name) seja único dentro da tabela.';