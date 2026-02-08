DROP TABLE IF EXISTS photographers;
CREATE TABLE IF NOT EXISTS photographers
(
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(), -- ID фотографа (первичный ключ)
    first_name        VARCHAR(50)         NOT NULL,               -- Имя фотографа (обязательное поле)
    last_name         VARCHAR(50)         NOT NULL,               -- Фамилия фотографа (обязательное поле)
    email             VARCHAR(100) UNIQUE NOT NULL,               -- Email фотографа (уникальный, обязательный)
    phone             VARCHAR(20),                                -- Телефон фотографа
    city              VARCHAR(50),                                -- Часовой пояс фотографа
    password          VARCHAR(255)     NOT NULL,               -- пароль
    role              VARCHAR(20)      DEFAULT 'PHOTOGRAPHER' NOT NULL, -- роль
    registration_date TIMESTAMP        DEFAULT CURRENT_TIMESTAMP, -- Дата регистрации
    is_active         BOOLEAN          DEFAULT true,              -- Активность фотографа на сайте
    updated_at        TIMESTAMP        DEFAULT CURRENT_TIMESTAMP  -- Дата обновления информации
);
