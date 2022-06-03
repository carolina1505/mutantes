create database mutants;

CREATE TABLE dna (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    dna VARCHAR(100) UNIQUE,
    tipo VARCHAR(150)
);

