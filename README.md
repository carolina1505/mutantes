# Código fuente API REST donde se pueda detectar si un humano es mutante enviando la secuencia de ADN

### Instrucciones 
API Url
URL local: http://localhost:8080

URL hosteada en Amazon: ec2-54-234-215-175.compute-1.amazonaws.com:8080

Servicios:

* GET: 54.234.215.175:8080/stats
* POST: 54.234.215.175:8080/mutant

* Request ejemplo para mutante:
{
    "dna":["ATGCGA","CAGTGC","TTAATT","AGACGG","GCCCCA","TCACTG"]
}

* Request ejemplo para no mutante:
{
    "dna":["ATGCGA","CAGTGC","TTAATT","AGACGG","GCGTCA","TCACTG"]
}



### BASE DE DATOS
Base de datos alojada en AWS 
* host: database-mutants.clmyprdgulhc.us-east-1.rds.amazonaws.com
* Puerto: 3306
# Creacion de BD local MySql
* create database mutants;
* CREATE TABLE dna (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    dna VARCHAR(100) UNIQUE,
    tipo VARCHAR(150)
);

