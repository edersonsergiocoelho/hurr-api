-- Cria a tabela "role_menu" se ainda não existir
CREATE TABLE IF NOT EXISTS role_menu (
    -- Identificador único da função (obrigatório)
    role_id UUID NOT NULL, 

    -- Identificador único do menu (obrigatório)
    menu_id UUID NOT NULL, 

    -- Identificador do tipo de menu (obrigatório)
    type_menu_id UUID NOT NULL, 

    -- Data de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, 

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, 

    -- Indicador de ativação do registro (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true, 

    -- Restrições para chaves primárias e únicas
    CONSTRAINT role_menu_pkey PRIMARY KEY (role_id, menu_id, type_menu_id),  -- Chave primária composta

    CONSTRAINT role_menu_to_role_fk FOREIGN KEY (role_id)  -- Restrição de integridade referencial para a tabela role
        REFERENCES role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION, 

    CONSTRAINT role_menu_to_menu_fk FOREIGN KEY (menu_id)  -- Restrição de integridade referencial para a tabela menu
        REFERENCES menu (menu_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION, 

    CONSTRAINT role_menu_to_type_menu_fk FOREIGN KEY (type_menu_id)  -- Restrição de integridade referencial para a tabela type_menu
        REFERENCES type_menu (type_menu_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos
COMMENT ON TABLE role_menu IS 'Tabela para associar menus às funções (roles).';

-- Comentários para cada coluna
COMMENT ON COLUMN role_menu.role_id IS 'Identificador único da função (role).';
COMMENT ON COLUMN role_menu.menu_id IS 'Identificador único do menu.';
COMMENT ON COLUMN role_menu.type_menu_id IS 'Identificador do tipo de menu.';
COMMENT ON COLUMN role_menu.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN role_menu.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN role_menu.enabled IS 'Indicador de ativação do registro.';

-- Comentários para as restrições
COMMENT ON CONSTRAINT role_menu_pkey ON role_menu IS 'Chave primária composta por role_id, menu_id e type_menu_id, identifica de forma única cada associação.';
COMMENT ON CONSTRAINT role_menu_to_role_fk ON role_menu IS 'Restrição de integridade referencial para a tabela role.';
COMMENT ON CONSTRAINT role_menu_to_menu_fk ON role_menu IS 'Restrição de integridade referencial para a tabela menu.';
COMMENT ON CONSTRAINT role_menu_to_type_menu_fk ON role_menu IS 'Restrição de integridade referencial para a tabela type_menu.';