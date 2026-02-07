DROP TABLE IF EXISTS photo;
CREATE TABLE IF NOT EXISTS photo(
    id UUID              PRIMARY KEY DEFAULT gen_random_uuid(),
    name                 VARCHAR(50),
    genre                VARCHAR(50),
    photographer_id      UUID REFERENCES photographers(id) ON DELETE CASCADE,
    download_date_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Дата загрузки
    file_path            VARCHAR(500), -- путь к файлу на сервере
    file_size            BIGINT,       -- размер файла в байтах
    mime_type            VARCHAR(100)  -- тип файла (image/jpeg, image/png)
);