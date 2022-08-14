-- CREATE DATABASE parcialdb;
USE parcialdb;

CREATE TABLE IF NOT EXISTS categoria (
                           `Categoria_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `Nombre` varchar(50) NOT NULL,
                           `CantMax` int(11) NULL
);

CREATE TABLE IF NOT EXISTS usuario (
                           `Nombre` varchar(255) NOT NULL,
                           `Apellido` varchar(255) NOT NULL,
                           `Mail` varchar(255) NOT NULL,
                           `Contraseña` varchar(255) NOT NULL,
                           `Id` INT NOT NULL,
                           `Categoria` INT NOT NULL,
                           `PagaMembresia` bool NOT NULL,
                           foreign key (Categoria) references categoria(Categoria_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE usuario
    ADD PRIMARY KEY (`Id`);

ALTER TABLE usuario
    MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

COMMIT;
