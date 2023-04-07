CREATE TABLE branches (
        id SERIAL PRIMARY KEY,
        name TEXT NOT NULL UNIQUE,
        cnpj TEXT NOT NULL UNIQUE,
        city VARCHAR(50) NOT NULL,
        uf VARCHAR(2) NOT NULL,
        type VARCHAR(20) NOT NULL,
        active BOOLEAN NOT NULL DEFAULT TRUE,
        created_at DATE NOT NULL DEFAULT NOW(),
        updated_at DATE DEFAULT NULL
);

CREATE TABLE sellers (
    id SERIAL PRIMARY KEY,
    register VARCHAR(12) NOT NULL UNIQUE,
    name TEXT NOT NULL,
    birthdate DATE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    contract_type VARCHAR(20) NOT NULL,
    branch_id INTEGER REFERENCES branches(id)
);
