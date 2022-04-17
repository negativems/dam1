DROP DATABASE IF EXISTS Netflix;
CREATE DATABASE Netflix;

USE Netflix;

CREATE TABLE users (
	id INT UNSIGNED AUTO_INCREMENT,
    username VARCHAR(16),
    email VARCHAR(50),
    password VARCHAR(65),
    PRIMARY KEY(id, username)
);

CREATE TABLE shows (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50),
    title VARCHAR(50),
    director VARCHAR(50),
    cast VARCHAR(50),
    country VARCHAR(50),
    date_added VARCHAR(50),
    release_year VARCHAR(50),
    rating VARCHAR(50),
    duration VARCHAR(50),
    listed_in VARCHAR(50),
    description VARCHAR(50)
);

CREATE TABLE temporary_codes (
	user_id INT UNSIGNED PRIMARY KEY,
    code VARCHAR(64),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users(username, password)
VALUES
("test", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08")
