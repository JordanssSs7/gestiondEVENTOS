-- ============================================================
--  Gestion de eventos - Bases de datos (una por microservicio)
--  Las tablas las crea Hibernate (ddl-auto: update); este script
--  solo crea las bases y documenta la estructura resultante.
-- ============================================================

-- PRODUCCION (perfil prod)
CREATE DATABASE IF NOT EXISTS bd_asistente;
CREATE DATABASE IF NOT EXISTS bd_evento;
CREATE DATABASE IF NOT EXISTS bd_inscripcion;

-- DESARROLLO (perfil dev, el que viene activo por defecto)
CREATE DATABASE IF NOT EXISTS bd_asistente_dev;
CREATE DATABASE IF NOT EXISTS bd_evento_dev;
CREATE DATABASE IF NOT EXISTS bd_inscripcion_dev;

-- ------------------------------------------------------------
-- bd_asistente(_dev)  -> asistente-service
-- ------------------------------------------------------------
-- CREATE TABLE asistente (
--     id         BIGINT AUTO_INCREMENT PRIMARY KEY,
--     documento  VARCHAR(15)  NOT NULL UNIQUE,
--     nombres    VARCHAR(100) NOT NULL,
--     apellidos  VARCHAR(100) NOT NULL,
--     correo     VARCHAR(100) NOT NULL,
--     telefono   VARCHAR(15),
--     estado     VARCHAR(20)  NOT NULL      -- ACTIVO | INACTIVO
-- );

-- ------------------------------------------------------------
-- bd_evento(_dev)  -> evento-service
-- ------------------------------------------------------------
-- CREATE TABLE evento (
--     id         BIGINT AUTO_INCREMENT PRIMARY KEY,
--     nombre     VARCHAR(150) NOT NULL,
--     lugar      VARCHAR(150) NOT NULL,
--     fecha      DATE         NOT NULL,
--     capacidad  INT          NOT NULL,      -- > 0
--     estado     VARCHAR(20)  NOT NULL       -- ACTIVO | CANCELADO | FINALIZADO
-- );

-- ------------------------------------------------------------
-- bd_inscripcion(_dev)  -> inscripcion-service
-- (id_evento e id_asistente NO son llaves foraneas: viven en otras BD)
-- ------------------------------------------------------------
-- CREATE TABLE inscripcion (
--     id           BIGINT AUTO_INCREMENT PRIMARY KEY,
--     id_evento    BIGINT      NOT NULL,
--     id_asistente BIGINT      NOT NULL,
--     fecha        DATE        NOT NULL,
--     estado       VARCHAR(20) NOT NULL      -- PENDIENTE | CONFIRMADA | CANCELADA
-- );

-- Verificacion
SHOW DATABASES LIKE 'bd_%';
