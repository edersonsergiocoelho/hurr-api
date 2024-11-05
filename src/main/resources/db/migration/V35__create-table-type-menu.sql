-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Cria a tabela "type_menu" se ainda não existir
CREATE TABLE IF NOT EXISTS type_menu
(
    -- Identificador único do tipo de menu (obrigatório)
    type_menu_id UUID DEFAULT uuid_generate_v4(), 

    -- Nome do tipo de menu (obrigatório, único)
    type_menu_name CHARACTER VARYING(100) NOT NULL, 

    -- Descrição do tipo de menu (obrigatório)
    description TEXT NOT NULL, 

    -- Data de criação do registro (obrigatório)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, 

    -- Data de modificação do registro
    modified_date TIMESTAMP WITHOUT TIME ZONE, 

    -- Indicador de ativação do registro (obrigatório)
    enabled BOOLEAN NOT NULL DEFAULT true, 

    -- Restrições para as chaves primárias e únicas
    CONSTRAINT type_menu_pkey PRIMARY KEY (type_menu_id),  -- Chave primária

    CONSTRAINT type_menu_unique UNIQUE (type_menu_name)  -- Garante que o nome do tipo de menu seja único
);

-- Comentários para a tabela e seus campos
COMMENT ON TABLE type_menu IS 'Tabela para armazenar os diferentes tipos de menus.';

-- Comentários para cada campo
COMMENT ON COLUMN type_menu.type_menu_id IS 'Identificador único do tipo de menu.';
COMMENT ON COLUMN type_menu.type_menu_name IS 'Nome do tipo de menu.';
COMMENT ON COLUMN type_menu.description IS 'Descrição do tipo de menu.';
COMMENT ON COLUMN type_menu.created_date IS 'Data de criação do registro.';
COMMENT ON COLUMN type_menu.modified_date IS 'Data de modificação do registro.';
COMMENT ON COLUMN type_menu.enabled IS 'Indicador de ativação do registro.';

-- Comentários para as restrições
COMMENT ON CONSTRAINT type_menu_pkey ON type_menu IS 'Chave primária da tabela, identifica de forma única cada tipo de menu.';
COMMENT ON CONSTRAINT type_menu_unique ON type_menu IS 'Garante que não haja dois tipos de menu com o mesmo nome.';