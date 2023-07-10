CREATE TABLE IF NOT EXISTS Content (
    id SERIAL PRIMARY KEY,
    title varchar(255) NOT NULL,
    description text,
    status varchar(20) NOT NULL,
    content_type varchar(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url varchar(255)
);
--
--INSERT INTO Content(title, desc, status,content_type, date_created)
--VALUES('My Spring Date Blog Post', 'A post about spring data', 'IDEA', 'ARTICLE', CURRENT_TIMESTAMP)