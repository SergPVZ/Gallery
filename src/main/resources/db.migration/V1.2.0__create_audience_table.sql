DROP TABLE IF EXISTS audience;
CREATE TABLE IF NOT EXISTS audiences(
    id UUID                  PRIMARY KEY DEFAULT gen_random_uuid(), -- ID пользователя (первичный ключ)
    name                     VARCHAR(50)  UNIQUE NOT NULL,          -- Ник пользователя (уникальный, обязательное поле)
    email                    VARCHAR(100) UNIQUE NOT NULL,          -- Email пользователя (уникальный, обязательный)
    city                     VARCHAR(50),                           -- Часовой пояс пользователя
    registration_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   -- Дата регистрации
    is_active                BOOLEAN   DEFAULT true,                -- Активность пользователя на сайте
    updated_at               TIMESTAMP DEFAULT CURRENT_TIMESTAMP    -- Дата обновления информации
);