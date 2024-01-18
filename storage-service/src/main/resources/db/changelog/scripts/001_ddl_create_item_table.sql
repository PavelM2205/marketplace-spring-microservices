CREATE TABLE IF NOT EXISTS item (
    id BIGSERIAL PRIMARY KEY,
    item_code TEXT NOT NULL UNIQUE,
    name TEXT NOT NULL,
    price NUMERIC NOT NULL,
    amount INT NOT NULL,
    description TEXT NOT NULL
);