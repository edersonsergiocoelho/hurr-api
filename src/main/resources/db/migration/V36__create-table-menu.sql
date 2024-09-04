CREATE TABLE IF NOT EXISTS menu (
    menu_id UUID DEFAULT uuid_generate_v4(), -- Identificador único do menu
    name CHARACTER VARYING(200) NOT NULL, -- Nome do menu
    description TEXT NOT NULL, -- Descrição do menu
    icon CHARACTER VARYING(100), -- Ícone associado ao menu
    menu_parent_id UUID, -- Identificador do menu pai (para hierarquia)
    url CHARACTER VARYING(200), -- URL associada ao menu
    menu_order INT, -- Ordem de exibição do menu
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data de criação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data de modificação do registro
    enabled boolean NOT NULL DEFAULT true, -- Indicador de ativação do registro
    CONSTRAINT menu_pkey PRIMARY KEY (menu_id), -- Chave primária da tabela (identificador único do menu)
    CONSTRAINT menu_to_parent_menu_fk FOREIGN KEY (menu_parent_id) -- Restrição de integridade referencial para a tabela menu (auto-referência)
        REFERENCES menu (menu_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE menu IS 'Tabela para armazenar informações dos menus e sua hierarquia';

COMMENT ON COLUMN menu.menu_id IS 'Identificador único do menu';
COMMENT ON COLUMN menu.name IS 'Nome do menu';
COMMENT ON COLUMN menu.icon IS 'Ícone associado ao menu';
COMMENT ON COLUMN menu.menu_parent_id IS 'Identificador do menu pai (para hierarquia)';
COMMENT ON COLUMN menu.url IS 'URL associada ao menu';
COMMENT ON COLUMN menu.menu_order IS 'Ordem de exibição do menu';
COMMENT ON COLUMN menu.created_date IS 'Data de criação do registro';
COMMENT ON COLUMN menu.modified_date IS 'Data de modificação do registro';
COMMENT ON COLUMN menu.enabled IS 'Indicador de ativação do registro';