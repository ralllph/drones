-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE drones (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    serial_number VARCHAR(255) UNIQUE,
    model VARCHAR(255),
    weight_limit DECIMAL,
    battery_capacity INT NOT NULL,
    state VARCHAR(255)
);

-- changeset liquibase:2
CREATE TABLE medication(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    weight DECIMAL,
    code VARCHAR(255),
    image VARCHAR(255),
    drones_id BIGINT,
    FOREIGN KEY(drones_id) REFERENCES drones(id)
)

-- changeset liquibase:3
CREATE TABLE users(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255)
)