CREATE TABLE hurr.user (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);