CREATE TABLE IF NOT EXISTS type_menu
(
    type_menu_id UUID DEFAULT uuid_generate_v4(), -- Identificador único do tipo de menu
    type_menu_name CHARACTER VARYING(100) NOT NULL UNIQUE, -- Nome do tipo de menu
    description TEXT NOT NULL, -- Descrição do tipo de menu
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data de criação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data de modificação do registro
    enabled boolean NOT NULL DEFAULT true, -- Indicador de ativação do registro
    CONSTRAINT type_menu_pkey PRIMARY KEY (type_menu_id), -- Chave primária da tabela (identificador único do tipo de menu)
    CONSTRAINT type_menu_unique UNIQUE (type_menu_name),
);

COMMENT ON TABLE type_menu IS 'Tabela para armazenar os diferentes tipos de menus';

COMMENT ON COLUMN type_menu.type_menu_id IS 'Identificador único do tipo de menu';
COMMENT ON COLUMN type_menu.type_menu_name IS 'Nome do tipo de menu';
COMMENT ON COLUMN type_menu.description IS 'Descrição do tipo de menu';
COMMENT ON COLUMN type_menu.created_date IS 'Data de criação do registro';
COMMENT ON COLUMN type_menu.modified_date IS 'Data de modificação do registro';
COMMENT ON COLUMN type_menu.enabled IS 'Indicador de ativação do registro';