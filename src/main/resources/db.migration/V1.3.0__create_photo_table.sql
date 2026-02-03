DROP TABLE IF EXISTS photo;
CREATE TABLE IF NOT EXISTS photo(
    id UUID              PRIMARY KEY DEFAULT gen_random_uuid(),
    name                 VARCHAR(50),
    genre                VARCHAR(50),
    download_date_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Дата загрузки
);