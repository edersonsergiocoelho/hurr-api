-- Define o search_path para o schema 'hurr'
SET search_path TO hurr;

-- Criação da tabela 'file' com comentários em cada campo
CREATE TABLE IF NOT EXISTS file
(
    -- Identificador único do arquivo
    file_id UUID DEFAULT uuid_generate_v4(),
    
    -- Tipo de conteúdo (MIME type) do arquivo
    content_type VARCHAR(50) NOT NULL,

    -- Nome original do arquivo antes do upload
    original_file_name VARCHAR(1000) NOT NULL,

    -- Conteúdo do arquivo em formato byte array
    data_as_byte_array BYTEA NOT NULL,

    -- Data de criação do registro (timestamp gerado automaticamente)
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,

    -- Data de modificação do registro, caso tenha sido alterado
    modified_date TIMESTAMP WITHOUT TIME ZONE,

    -- Indica se o arquivo está habilitado ou não
    enabled BOOLEAN NOT NULL DEFAULT true,

    -- Definição da chave primária da tabela
    CONSTRAINT file_pkey PRIMARY KEY (file_id)
);

-- Adiciona comentários para a tabela
COMMENT ON TABLE file IS 'Tabela que armazena informações sobre arquivos carregados, incluindo nome, tipo de conteúdo, dados binários, e datas de criação/modificação';

-- Adiciona comentário para o campo 'file_id'
COMMENT ON COLUMN file.file_id IS 'Identificador único (UUID) gerado automaticamente para cada arquivo.';

-- Adiciona comentário para o campo 'content_type'
COMMENT ON COLUMN file.content_type IS 'Tipo de conteúdo (MIME type) do arquivo, ex: image/jpeg, application/pdf.';

-- Adiciona comentário para o campo 'original_file_name'
COMMENT ON COLUMN file.original_file_name IS 'Nome original do arquivo conforme foi carregado pelo usuário.';

-- Adiciona comentário para o campo 'data_as_byte_array'
COMMENT ON COLUMN file.data_as_byte_array IS 'Dados binários do arquivo armazenados como byte array (BYTEA).';

-- Adiciona comentário para o campo 'created_date'
COMMENT ON COLUMN file.created_date IS 'Data e hora em que o arquivo foi carregado e registrado no sistema.';

-- Adiciona comentário para o campo 'modified_date'
COMMENT ON COLUMN file.modified_date IS 'Data e hora da última modificação do arquivo, se houver.';

-- Adiciona comentário para o campo 'enabled'
COMMENT ON COLUMN file.enabled IS 'Indica se o arquivo está ativo (true) ou desativado (false).';