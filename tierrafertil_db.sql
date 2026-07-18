CREATE DATABASE IF NOT EXISTS tierrafertil_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE tierrafertil_db;

CREATE TABLE IF NOT EXISTS roles (
    id_rol      INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol  VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario   INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    correo       VARCHAR(150) NOT NULL UNIQUE,
    contrasena   VARCHAR(255) NOT NULL,
    id_rol       INT NOT NULL,
    CONSTRAINT fk_usuarios_rol
        FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tipo_propiedad (
    id_tipo      INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo  VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS ubicacion (
    id_ubicacion  INT AUTO_INCREMENT PRIMARY KEY,
    provincia     VARCHAR(50) NOT NULL,
    canton        VARCHAR(50) NOT NULL,
    distrito      VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS estado_propiedad (
    id_estado      INT AUTO_INCREMENT PRIMARY KEY,
    nombre_estado  VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS propiedades (
    id_propiedad  INT AUTO_INCREMENT PRIMARY KEY,
    titulo        VARCHAR(150) NOT NULL,
    descripcion   TEXT,
    precio        DECIMAL(12,2) NOT NULL,
    id_agente     INT NOT NULL,
    id_tipo       INT NOT NULL,
    id_ubicacion  INT NOT NULL,
    id_estado     INT NOT NULL,
    CONSTRAINT fk_propiedades_agente
        FOREIGN KEY (id_agente) REFERENCES usuarios(id_usuario)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT fk_propiedades_tipo
        FOREIGN KEY (id_tipo) REFERENCES tipo_propiedad(id_tipo)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT fk_propiedades_ubicacion
        FOREIGN KEY (id_ubicacion) REFERENCES ubicacion(id_ubicacion)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT fk_propiedades_estado
        FOREIGN KEY (id_estado) REFERENCES estado_propiedad(id_estado)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS imagenes (
    id_imagen     INT AUTO_INCREMENT PRIMARY KEY,
    id_propiedad  INT NOT NULL,
    ruta_imagen   VARCHAR(255) NOT NULL,
    CONSTRAINT fk_imagenes_propiedad
        FOREIGN KEY (id_propiedad) REFERENCES propiedades(id_propiedad)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS solicitudes_visitas (
    id_solicitud     INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente       INT NOT NULL,
    id_propiedad     INT NOT NULL,
    fecha_visita     DATETIME NOT NULL,
    estado_solicitud VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    CONSTRAINT fk_solicitudes_cliente
        FOREIGN KEY (id_cliente) REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_solicitudes_propiedad
        FOREIGN KEY (id_propiedad) REFERENCES propiedades(id_propiedad)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT chk_estado_solicitud
        CHECK (estado_solicitud IN ('PENDIENTE', 'APROBADA', 'RECHAZADA'))
);


INSERT IGNORE INTO roles (nombre_rol) VALUES ('ADMIN'), ('AGENTE'), ('CLIENTE');

INSERT IGNORE INTO estado_propiedad (nombre_estado) VALUES ('Disponible'), ('Vendida'), ('Alquilada');

INSERT IGNORE INTO tipo_propiedad (nombre_tipo) VALUES ('Casa'), ('Apartamento'), ('Local comercial'), ('Terreno');


SELECT id_usuario, nombre, correo FROM usuarios;

UPDATE usuarios
   SET id_rol = (SELECT id_rol FROM roles WHERE nombre_rol = 'AGENTE')
   WHERE id_usuario = 1;