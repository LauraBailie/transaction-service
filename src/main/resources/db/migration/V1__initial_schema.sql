-- V1__initial_schema.sql

-- Users table (from Step 1)
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Transactions table (assuming your existing entity)
CREATE TABLE IF NOT EXISTS transaction (
    id BIGSERIAL PRIMARY KEY,
    amount DOUBLE PRECISION,
    currency VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status VARCHAR(255),
    timestamp TIMESTAMP(6),
    user_id BIGINT REFERENCES users(id)  -- Optional: link to user if you want ownership
);