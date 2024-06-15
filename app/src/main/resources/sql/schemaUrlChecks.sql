

CREATE TABLE url_checks (
    id SERIAL PRIMARY KEY,
    status_code INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    h_1 VARCHAR(255) NOT NULL,
    description text NOT NULL,
    url_id INT NOT NULL,
    FOREIGN KEY (url_id) REFERENCES urls(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
