CREATE TABLE empleado ( 
	cargo VARCHAR(31) NOT NULL, 
	id BIGINT NOT NULL, 
	apellido VARCHAR(255), 
	nombre VARCHAR(255), 
	occupied BOOLEAN DEFAULT false NOT NULL, 
	PRIMARY KEY (id) 
);