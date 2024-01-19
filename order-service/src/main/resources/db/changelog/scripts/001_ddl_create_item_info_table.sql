CREATE TABLE IF NOT EXISTS item_info (
    id BIGSERIAL PRIMARY KEY,
    item_id BIGINT NOT NULL,
    item_code TEXT NOT NULL,
    count INT NOT NULL,
    price DECIMAL NOT NULL,
    order_id BIGINT REFERENCES order_table(id)
);