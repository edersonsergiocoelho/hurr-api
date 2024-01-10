CREATE TABLE IF NOT EXISTS file
(
    file_id UUID DEFAULT uuid_generate_v4(),
    content_type VARCHAR(50) NOT NULL,
    original_file_name VARCHAR(1000) NOT NULL,
    data_as_byte_array BYTEA NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT file_pkey PRIMARY KEY (file_id)
);