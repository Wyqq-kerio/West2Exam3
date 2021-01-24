DROP DATABASE db;
CREATE DATABASE  IF NOT EXISTS db;

CREATE TABLE country( 
	iso INT, 
	continent VARCHAR(20), 
	capital_city VARCHAR(20), 
	life_expectancy DOUBLE, 
	abbreviation VARCHAR(10),
	confirmed INT, 
	population INT,
	sq_km_area INT, 
	recovered INT, 
	elevation_in_meters VARCHAR(10), 
	location VARCHAR(50), 
	deaths INT

);

CREATE TABLE district (
	iso_dis INT, 
	provinceName VARCHAR(30),
	recovered INT, 
	confirmed INT, 
	updated VARCHAR(60), 
	deaths INT
);
