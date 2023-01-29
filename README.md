# ProyectoBiblioteca
Aplicación en JavaFX de una biblioteca como proyecto de la asignatura Acceso a Datos

TABLA libros CAMBIO A libro (sin 's')

script con CAMBIO añadiendo columna imagen
 e insertando algunos datos

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `biblioteca`;

CREATE TABLE IF NOT EXISTS `libro` (
  `ISBN` varchar(13) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `titulo` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `categoria` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `autor` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `idioma` varchar(20) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `paginas` varchar(5) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `ejemplares` int(3) NOT NULL,
  `disponible` int(3) DEFAULT NULL,
  `imagen` blob DEFAULT NULL,
  PRIMARY KEY (`ISBN`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` (`ISBN`, `titulo`, `categoria`, `autor`, `idioma`, `paginas`, `ejemplares`, `disponible`, `imagen`) VALUES
	('123456787DD', '1984', 'ficcion', 'George Orwell', 'ingles', '328', 3, 3, NULL),
	('123456788CC', 'Los Miserables', 'ficciÃ³n', 'VÃ­ctor Hugo', 'francÃ©s', '1464', 2, 2, NULL),
	('123456789BB', 'El Alquimista', 'ficciÃ³n', 'Paulo Coelho', 'espaÃ±ol', '111', 3, 3, NULL),
	('123456789ii', 'a', 'j', 'aa', 'aaa', '11', 1, NULL, NULL);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `prestamos` (
  `DNI_usuario` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `ISBN` varchar(13) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `fecha_salida` date NOT NULL,
  `fecha_devolucion` date NOT NULL,
  KEY `FK_prestamos_usuarios` (`DNI_usuario`),
  KEY `FK_prestamos_libros` (`ISBN`),
  CONSTRAINT `FK_prestamos_libros` FOREIGN KEY (`ISBN`) REFERENCES `libro` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_prestamos_usuarios` FOREIGN KEY (`DNI_usuario`) REFERENCES `usuarios` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `usuarios` (
  `DNI` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(20) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `apellidos` varchar(40) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `domicilio` varchar(250) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `telefono` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `sancionado` tinyint(1) DEFAULT 0,
  `clave` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`DNI`, `nombre`, `apellidos`, `domicilio`, `telefono`, `email`, `sancionado`, `clave`) VALUES
	('26654342f', 'a', 'aa', 'aaa', '954665544', 'a@gmail.com', 0, '123456aaAA'),
	('28860497l', 'Ana', 'LÃ³pez', 'C/ Sol 1', '954666666', 'ana@gmail.com', 0, NULL),
	('28860645K', 'Luisa', 'RuÃ­z', 'C/ Sol 1', '954528888', 'luisa@gmail.com', 0, NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

