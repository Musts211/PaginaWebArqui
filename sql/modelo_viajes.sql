DROP DATABASE IF EXISTS sistema_viajes;
CREATE DATABASE sistema_viajes;
USE sistema_viajes;

CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  telefono VARCHAR(20)
);

CREATE TABLE autobus (
  id INT AUTO_INCREMENT PRIMARY KEY,
  placa VARCHAR(10) UNIQUE CHECK (placa REGEXP '^[A-Z]{2}-[0-9]{4}$'),
  capacidad INT
);

CREATE TABLE chofer (
  id INT AUTO_INCREMENT PRIMARY KEY,
  codigo VARCHAR(10) UNIQUE CHECK (codigo REGEXP '^CH-[0-9]{4}$'),
  nombre VARCHAR(100)
);

CREATE TABLE viaje (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fecha DATE,
  hora TIME,
  origen VARCHAR(100),
  destino VARCHAR(100),
  id_autobus INT,
  id_chofer INT,
  FOREIGN KEY (id_autobus) REFERENCES autobus(id),
  FOREIGN KEY (id_chofer) REFERENCES chofer(id)
);

CREATE TABLE reserva (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fecha_reserva DATE,
  id_usuario INT,
  id_viaje INT,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id),
  FOREIGN KEY (id_viaje) REFERENCES viaje(id)
);

-- Datos de prueba
INSERT INTO usuario (nombre, email, telefono)
VALUES 
('Ana Ramos', 'ana@email.com', '987654321'),
('Carlos Peña', 'carlos@email.com', '955444333');

INSERT INTO autobus (placa, capacidad)
VALUES 
('AB-1234', 40), 
('CD-5678', 50);

INSERT INTO chofer (codigo, nombre)
VALUES 
('CH-0001', 'Luis Pérez'), 
('CH-0002', 'Carlos Gómez');

INSERT INTO viaje (fecha, hora, origen, destino, id_autobus, id_chofer)
VALUES 
('2025-06-01', '08:00:00', 'Piura', 'Lima', 1, 1),
('2025-06-02', '14:00:00', 'Piura', 'Chiclayo', 2, 2);

INSERT INTO reserva (fecha_reserva, id_usuario, id_viaje)
VALUES 
(CURDATE(), 1, 1),
(CURDATE(), 2, 2);