CREATE TABLE IF NOT EXISTS photo_audience_favorites (
    photo_id        UUID      REFERENCES photo(id) ON DELETE CASCADE,
    audience_id     UUID      REFERENCES audience(id) ON DELETE CASCADE,
    added_at        TIMESTAMP DEFAULT   CURRENT_TIMESTAMP,
    PRIMARY KEY (photo_id, audience_id)
);