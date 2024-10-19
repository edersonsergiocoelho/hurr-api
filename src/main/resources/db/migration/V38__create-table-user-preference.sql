-- Cria a tabela "user_preference" se ainda não existir
CREATE TABLE IF NOT EXISTS user_preference (

    user_preference_id UUID NOT NULL, -- Identificador único da preferência do usuário

    user_id UUID NOT NULL, -- Identificador único do usuário

    language VARCHAR(10), -- Preferência de linguagem (ex: 'en', 'pt')

    theme VARCHAR(50), -- Tema escolhido pelo usuário (ex: 'dark', 'light')

    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data de criação do registro

    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data de modificação do registro

    enabled BOOLEAN NOT NULL DEFAULT true, -- Indicador se a preferência está ativa ou não

    CONSTRAINT user_preference_pkey PRIMARY KEY (user_preference_id), -- Chave primária da tabela

    CONSTRAINT user_preference_to_user_fk FOREIGN KEY (user_id) -- Relacionamento com a tabela "user"
        REFERENCES "user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos
COMMENT ON TABLE user_preference IS 'Tabela para armazenar preferências de usuários, como idioma e tema.';

-- Comentários para cada coluna
COMMENT ON COLUMN user_preference.user_preference_id IS 'Identificador único da preferência do usuário.';
COMMENT ON COLUMN user_preference.user_id IS 'Identificador único do usuário, referenciado pela tabela "user".';
COMMENT ON COLUMN user_preference.language IS 'Preferência de linguagem escolhida pelo usuário (ex: "en", "pt").';
COMMENT ON COLUMN user_preference.theme IS 'Tema escolhido pelo usuário (ex: "dark", "light").';
COMMENT ON COLUMN user_preference.created_date IS 'Data de criação da preferência do usuário.';
COMMENT ON COLUMN user_preference.modified_date IS 'Data da última modificação da preferência do usuário.';
COMMENT ON COLUMN user_preference.enabled IS 'Indicador se a preferência do usuário está ativa (true) ou desativada (false).';

-- Comentários para as restrições
COMMENT ON CONSTRAINT user_preference_pkey ON user_preference IS 'Chave primária única da tabela user_preference, composta pelo campo user_preference_id.';
COMMENT ON CONSTRAINT user_preference_to_user_fk ON user_preference IS 'Restrição de integridade referencial, associando cada preferência a um usuário da tabela "user".';