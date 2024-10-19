-- Cria a tabela "user" se ela ainda não existir
CREATE TABLE IF NOT EXISTS "user"
(
    -- Identificador único do usuário, gerado automaticamente como UUID
    user_id UUID DEFAULT uuid_generate_v4(),
    
    -- ID do usuário associado a um provedor externo (ex: Google, Facebook)
    provider_user_id CHARACTER VARYING(100),
    
    -- Endereço de e-mail único do usuário
    email CHARACTER VARYING(200),
    
    -- Nome exibido pelo usuário
    display_name CHARACTER VARYING(200) NOT NULL,
    
    -- Senha do usuário (armazenada de forma segura, como hash)
    password CHARACTER VARYING(200) NOT NULL,
    
    -- Código de verificação para redefinição de senha
    forgot_password_verification_code CHARACTER VARYING(6),
    
    -- Indica se o código de redefinição de senha foi validado
    forgot_password_validated BOOLEAN NOT NULL DEFAULT false,
    
    -- Provedor de autenticação (ex: local, Google, Facebook)
    provider CHARACTER VARYING(50) NOT NULL,
    
    -- Identificador da foto do perfil do usuário, referenciando a tabela 'file'
    photo_file_id UUID,
    
    -- Indica se a foto foi validada
    photo_validated BOOLEAN NOT NULL DEFAULT false,
    
    -- URL da imagem de perfil (caso seja usada uma URL externa)
    image_url TEXT,
    
    -- Data de criação do registro de usuário
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    
    -- Data de modificação do registro de usuário
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    
    -- Indica se o usuário está habilitado/ativo
    enabled BOOLEAN NOT NULL DEFAULT true,
    
    -- Define a chave primária para a tabela 'user' baseada no campo 'user_id'
    CONSTRAINT user_pkey PRIMARY KEY (user_id),

    CONSTRAINT user_unique UNIQUE (email),

    -- Define a chave estrangeira que referencia o campo 'file_id' da tabela 'file'
    CONSTRAINT user_to_file_fk FOREIGN KEY (photo_file_id)
        REFERENCES file (file_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Comentários para a tabela e seus campos
COMMENT ON TABLE "user" IS 'Tabela que armazena informações dos usuários, incluindo autenticação, dados pessoais e foto de perfil.';

-- Comentários para cada campo
COMMENT ON COLUMN "user".user_id IS 'Identificador único do usuário, gerado automaticamente como UUID.';
COMMENT ON COLUMN "user".provider_user_id IS 'ID do usuário associado a um provedor externo (ex: Google, Facebook).';
COMMENT ON COLUMN "user".email IS 'Endereço de e-mail único do usuário.';
COMMENT ON COLUMN "user".display_name IS 'Nome exibido pelo usuário.';
COMMENT ON COLUMN "user".password IS 'Senha do usuário, armazenada em formato seguro.';
COMMENT ON COLUMN "user".forgot_password_verification_code IS 'Código de verificação para redefinição de senha.';
COMMENT ON COLUMN "user".forgot_password_validated IS 'Indica se o código de redefinição de senha foi validado.';
COMMENT ON COLUMN "user".provider IS 'Provedor de autenticação, como local, Google ou Facebook.';
COMMENT ON COLUMN "user".photo_file_id IS 'Identificador da foto do perfil do usuário, referenciando a tabela file.';
COMMENT ON COLUMN "user".photo_validated IS 'Indica se a foto do perfil foi validada.';
COMMENT ON COLUMN "user".image_url IS 'URL da imagem de perfil do usuário, caso seja usada uma URL externa.';
COMMENT ON COLUMN "user".created_date IS 'Data de criação do registro do usuário.';
COMMENT ON COLUMN "user".modified_date IS 'Data de modificação do registro do usuário.';
COMMENT ON COLUMN "user".enabled IS 'Indica se o usuário está habilitado/ativo.';

-- Comentários para as constraints
COMMENT ON CONSTRAINT user_pkey ON "user" IS 'Chave primária da tabela user, baseada no campo user_id.';
COMMENT ON CONSTRAINT user_unique ON "user" IS 'Restrições de unicidade para garantir que o e-mail seja único.';
COMMENT ON CONSTRAINT user_to_file_fk ON "user" IS 'Chave estrangeira que referencia o campo file_id da tabela file, associando uma foto de perfil ao usuário.';