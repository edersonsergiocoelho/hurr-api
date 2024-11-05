-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "menu" se ainda não existir
CREATE TABLE IF NOT EXISTS menu (
    -- Identificador único do menu (obrigatório)
    menu_id UUID DEFAULT uuid_generate_v4(), 

    -- Nome do menu (obrigatório)
    name CHARACTER VARYING(200) NOT NULL, 

    -- Descrição do menu (obrigatório)
    description TEXT NOT NULL, 

    -- Ícone associado ao menu
    icon CHARACTER VARYING(100), 

    -- Identificador do menu pai (para hierarquia)
    menu_parent_id UUID, 

    -- URL associada ao menu
    url CHARACTER VARYING(200), 

    -- Ordem de exibição do menu
    menu_order INT, 

    -- Data de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, 

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, 

    -- Indicador de ativação do registro (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true, 

    -- Restrições para as chaves primárias e únicas
    CONSTRAINT menu_pkey PRIMARY KEY (menu_id),  -- Chave primária

    CONSTRAINT menu_unique UNIQUE (name),  -- Garante que o nome do menu seja único

    CONSTRAINT menu_to_parent_menu_fk FOREIGN KEY (menu_parent_id)  -- Restrição de integridade referencial para a tabela menu (auto-referência)
        REFERENCES menu (menu_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos
COMMENT ON TABLE menu IS 'Tabela para armazenar informações dos menus e sua hierarquia.';

-- Comentários para cada campo
COMMENT ON COLUMN menu.menu_id IS 'Identificador único do menu.';
COMMENT ON COLUMN menu.name IS 'Nome do menu.';
COMMENT ON COLUMN menu.description IS 'Descrição do menu.';
COMMENT ON COLUMN menu.icon IS 'Ícone associado ao menu.';
COMMENT ON COLUMN menu.menu_parent_id IS 'Identificador do menu pai (para hierarquia).';
COMMENT ON COLUMN menu.url IS 'URL associada ao menu.';
COMMENT ON COLUMN menu.menu_order IS 'Ordem de exibição do menu.';
COMMENT ON COLUMN menu.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN menu.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN menu.enabled IS 'Indicador de ativação do registro.';

-- Comentários para as restrições
COMMENT ON CONSTRAINT menu_pkey ON menu IS 'Chave primária da tabela, identifica de forma única cada menu.';
COMMENT ON CONSTRAINT menu_unique ON menu IS 'Garante que não haja dois menus com o mesmo nome.';
COMMENT ON CONSTRAINT menu_to_parent_menu_fk ON menu IS 'Restrição de integridade referencial para a auto-referência do menu (menu pai).';