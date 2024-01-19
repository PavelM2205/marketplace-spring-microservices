CREATE TABLE IF NOT EXISTS order_table (
    id BIGSERIAL PRIMARY KEY,
    order_code TEXT NOT NULL,
    created TIMESTAMP NOT NULL,
    is_paid BOOLEAN NOT NULL
);