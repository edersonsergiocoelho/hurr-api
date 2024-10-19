-- Cria o schema 'HURR' se ele ainda não existir
CREATE SCHEMA IF NOT EXISTS HURR;

-- Define o fuso horário para 'America/Sao_Paulo' para a sessão atual
SET TIME ZONE 'America/Sao_Paulo';

-- Cria a extensão 'uuid-ossp' (usada para geração de UUIDs) no schema 'HURR', se ela ainda não existir
CREATE EXTENSION IF NOT EXISTS "uuid-ossp" SCHEMA HURR;

-- Cria o schema 'HURR', que será o contêiner para objetos do banco de dados
COMMENT ON SCHEMA HURR IS 'Schema HURR que agrupa objetos de banco de dados, como tabelas e extensões, relacionados à aplicação.';

-- Cria a extensão 'uuid-ossp', que permite a geração de UUIDs, dentro do schema 'HURR'
COMMENT ON EXTENSION "uuid-ossp" IS 'Extensão que adiciona suporte para a geração de UUIDs utilizando funções como uuid_generate_v4, armazenada no schema HURR.';