CREATE TABLE sellers (
    id SERIAL PRIMARY KEY,
    register VARCHAR(12) NOT NULL UNIQUE,
    name TEXT NOT NULL,
    birthdate DATE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    contract_type VARCHAR(20) NOT NULL,
    branch_id INTEGER DEFAULT NULL)
;
