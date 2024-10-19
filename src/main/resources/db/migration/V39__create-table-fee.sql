-- Cria a tabela "fee" se ainda não existir
CREATE TABLE IF NOT EXISTS fee (

    fee_id UUID NOT NULL DEFAULT gen_random_uuid(), -- Identificador único da taxa

    fee_type VARCHAR(50) NOT NULL, -- Tipo de taxa (ex: 'commission', 'service fee')

    amount DECIMAL(10, 2) NOT NULL, -- Valor da taxa

    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp, -- Data de criação do registro

    modified_date TIMESTAMP WITHOUT TIME ZONE, -- Data de modificação do registro

    enabled BOOLEAN NOT NULL DEFAULT true, -- Indicador se a taxa está ativa ou não

    CONSTRAINT fee_pkey PRIMARY KEY (fee_id) -- Chave primária da tabela
);

-- Comentários para a tabela e seus campos
COMMENT ON TABLE fee IS 'Tabela para armazenar as taxas aplicáveis no site.';

-- Comentários para cada coluna
COMMENT ON COLUMN fee.fee_id IS 'Identificador único da taxa.';
COMMENT ON COLUMN fee.fee_type IS 'Tipo de taxa (ex: "commission", "service fee").';
COMMENT ON COLUMN fee.amount IS 'Valor monetário associado a esta taxa.';
COMMENT ON COLUMN fee.created_date IS 'Data de criação da taxa.';
COMMENT ON COLUMN fee.modified_date IS 'Data da última modificação da taxa.';
COMMENT ON COLUMN fee.enabled IS 'Indicador se a taxa está ativa (true) ou desativada (false).';

-- Comentários para as restrições
COMMENT ON CONSTRAINT fee_pkey ON fee IS 'Chave primária única da tabela fee, composta pelo campo fee_id.';