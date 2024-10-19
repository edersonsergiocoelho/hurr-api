-- Cria a tabela "user_role" se ela ainda não existir
CREATE TABLE IF NOT EXISTS user_role
(
    -- Identificador do papel associado ao usuário
    role_id UUID NOT NULL,
    
    -- Identificador do usuário associado ao papel
    user_id UUID NOT NULL,
    
    -- Data de criação do registro de associação entre usuário e papel
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    
    -- Data de modificação do registro de associação
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    
    -- Indica se a associação está habilitada/ativa
    enabled BOOLEAN NOT NULL DEFAULT true,
    
    -- Define a chave primária para a tabela 'user_role' baseada nos campos 'role_id' e 'user_id'
    CONSTRAINT user_role_pkey PRIMARY KEY (role_id, user_id),
    
    -- Define a chave estrangeira que referencia o papel na tabela 'role'
    CONSTRAINT user_role_to_role_fk FOREIGN KEY (role_id)
        REFERENCES role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    
    -- Define a chave estrangeira que referencia o usuário na tabela 'user'
    CONSTRAINT user_role_to_user_fk FOREIGN KEY (user_id)
        REFERENCES "user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos

-- Tabela que armazena associações entre usuários e papéis (roles) no sistema
COMMENT ON TABLE user_role IS 'Tabela que armazena associações entre usuários e papéis, incluindo datas de criação/modificação e status de habilitação.';

-- Comentários para cada campo

COMMENT ON COLUMN user_role.role_id IS 'Identificador do papel associado ao usuário.';
COMMENT ON COLUMN user_role.user_id IS 'Identificador do usuário associado ao papel.';
COMMENT ON COLUMN user_role.created_date IS 'Data de criação do registro de associação entre usuário e papel.';
COMMENT ON COLUMN user_role.modified_date IS 'Data de modificação do registro de associação.';
COMMENT ON COLUMN user_role.enabled IS 'Indica se a associação está habilitada/ativa.';

-- Comentários para as constraints
COMMENT ON CONSTRAINT user_role_pkey ON "user_role" IS 'Chave primária da tabela user_role, baseada nos campos role_id e user_id.';
COMMENT ON CONSTRAINT user_role_to_role_fk ON "user_role" IS 'Chave estrangeira que referencia o papel na tabela role.';
COMMENT ON CONSTRAINT user_role_to_user_fk ON "user_role" IS 'Chave estrangeira que referencia o usuário na tabela user.';
