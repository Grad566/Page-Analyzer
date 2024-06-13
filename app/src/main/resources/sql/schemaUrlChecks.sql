DROP TABLE IF EXISTS url_checks;

CREATE TABLE url_checks (
    id SERIAL PRIMARY KEY,
    status_code INTEGER NOT NULL,
    title VARCHAR(255),
    h_1 VARCHAR(255),
    description text,
    url_id INT NOT NULL,
    FOREIGN KEY (url_id) REFERENCES sites(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
