CREATE TABLE IF NOT EXISTS role_menu (
    role_id UUID NOT NULL, -- Identificador único da função (role)
    menu_id UUID NOT NULL, -- Identificador único do menu
    type_menu_id UUID NOT NULL, -- Identificador do tipo de menu
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data de criação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data de modificação do registro
    enabled boolean NOT NULL DEFAULT true, -- Indicador de ativação do registro
    CONSTRAINT role_menu_pkey PRIMARY KEY (role_id, menu_id, type_menu_id), -- Chave primária composta por role_id e menu_id
    CONSTRAINT role_menu_unique UNIQUE (role_id, menu_id, type_menu_id),
    CONSTRAINT role_menu_to_role_fk FOREIGN KEY (role_id)
        REFERENCES role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION, -- Restrição de integridade referencial para a tabela role
    CONSTRAINT role_menu_to_menu_fk FOREIGN KEY (menu_id)
        REFERENCES menu (menu_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION, -- Restrição de integridade referencial para a tabela menu
    CONSTRAINT role_menu_to_type_menu_fk FOREIGN KEY (type_menu_id) -- Restrição de integridade referencial para a tabela type_menu
        REFERENCES type_menu (type_menu_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE role_menu IS 'Tabela para associar menus às funções (roles)';

COMMENT ON COLUMN role_menu.role_id IS 'Identificador único da função (role)';
COMMENT ON COLUMN role_menu.menu_id IS 'Identificador único do menu';
COMMENT ON COLUMN role_menu.type_menu_id IS 'Identificador do tipo de menu';
COMMENT ON COLUMN role_menu.created_date IS 'Data de criação do registro';
COMMENT ON COLUMN role_menu.modified_date IS 'Data de modificação do registro';
COMMENT ON COLUMN role_menu.enabled IS 'Indicador de ativação do registro';