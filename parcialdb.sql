CREATE DATABASE parcialdb;
USE parcialdb;


CREATE TABLE IF NOT EXISTS flight(
    `Id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Number` varchar(50) NULL,
    `Iata` varchar(50) NULL,
    `Icao` varchar(50) NULL,
    `Codeshared` varchar(50) NULL
);

CREATE TABLE IF NOT EXISTS departure(
    `Id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Airport` varchar(50) ,
    `Timezone` varchar(50) ,
    `Iata` varchar(50) ,
    `Icao` varchar(50) ,
    `Terminal` varchar(50) ,
    `Gate` varchar(50) ,
    `Delay` varchar(50) ,
    `Scheduled` varchar(50) ,
    `Estimated` varchar(50) ,
    `Actual` varchar(50) ,
    `Estimated_runway` varchar(50) ,
    `Actual_runway` varchar(50)
);

CREATE TABLE IF NOT EXISTS arrival(
    `Id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Airport` varchar(50) ,
    `Timezone` varchar(50) ,
    `Iata` varchar(50) ,
    `Icao` varchar(50) ,
    `Terminal` varchar(50) ,
    `Gate` varchar(50) ,
    `Baggage` varchar(50) ,
    `Delay` varchar(50) ,
    `Scheduled` varchar(50) ,
    `Estimated` varchar(50) ,
    `Actual` varchar(50) ,
    `Estimated_runway` varchar(50) ,
    `Actual_runway` varchar(50)
);

CREATE TABLE IF NOT EXISTS aircraft(
    `Id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Registration` varchar(50) ,
    `Iata` varchar(50) ,
    `Icao` varchar(50) ,
    `Icao24` varchar(50)
);

CREATE TABLE IF NOT EXISTS airline(
    `Id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Name` varchar(50) ,
    `Iata` varchar(50) ,
    `Icao` varchar(50)
);


CREATE TABLE IF NOT EXISTS vuelo(
    `Id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Flight_date` varchar(255) NULL,
    `Flight_status` varchar(45) NULL,
    `Departure` INT NULL,
    `Arrival` INT NULL,
    `Flight` INT NULL,
    `Aircraft` INT NULL,
    `Airline` INT NULL,
    `Tanque` INT NULL,
    `Estado` varchar(50) NOT NULL,
    `Comida` varchar(50) ,
    foreign key (Departure) references departure(Id) on delete cascade,
    foreign key (Arrival) references arrival(Id) on delete cascade,
    foreign key (Flight) references flight(Id) on delete cascade,
    foreign key (Aircraft) references aircraft(Id) on delete cascade,
    foreign key (Airline) references airline(Id) on delete cascade
);

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
