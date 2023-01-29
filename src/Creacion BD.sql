-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         10.6.11-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para biblioteca
CREATE DATABASE IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `biblioteca`;

-- Volcando estructura para tabla biblioteca.libros
CREATE TABLE IF NOT EXISTS `libros` (
                                        `ISBN` varchar(13) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                        `titulo` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                        `autor` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                        `categoria` set('Terror','Ciencia ficción','Drama','Bélica','Infantil','Comedia','Aventuras','Cómic') CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                        `idioma` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                        `paginas` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                        `ejemplares` int(3) NOT NULL DEFAULT 0,
                                        `disponible` int(3) NOT NULL DEFAULT 0,
                                        PRIMARY KEY (`ISBN`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla biblioteca.libros: ~10 rows (aproximadamente)
DELETE FROM `libros`;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` (`ISBN`, `titulo`, `autor`, `categoria`, `idioma`, `paginas`, `ejemplares`, `disponible`) VALUES
                                                                                                                   ('123456222PP', 'Cumbres borrascosas', 'Emily Bronte', 'Drama', 'inglés', '460', 3, 3),
                                                                                                                   ('123456333CC', 'El Principito', 'Antoine de Saint-Exupéry', 'Infantil', 'francés', '91', 4, 4),
                                                                                                                   ('123456444JJ', 'El retrato de Dorian Grey', 'Oscar Wilde', 'Terror', 'inglés', '248', 3, 3),
                                                                                                                   ('123456766FF', 'El Alquimista', 'Paulo Coelho', '', 'español', '208', 2, 1),
                                                                                                                   ('123456787EE', 'Los Miserables', 'Víctor Hugo', 'Drama', 'francés', '1464', 1, 1),
                                                                                                                   ('123456788CC', 'Nada', 'Carmen Laforet', '', 'español', '216', 2, 2),
                                                                                                                   ('123456789BB', '1984', 'George Orwell', '', 'ingles', '328', 1, 1),
                                                                                                                   ('123456888TT', 'Cien años de Soledad', 'Gabriel García Márquez', '', 'español', '417', 3, 3),
                                                                                                                   ('123456987GG', 'La Divina Comedia', 'Dante', 'Aventuras', 'italiano', '100', 2, 2),
                                                                                                                   ('123456999RR', 'Un mundo feliz', 'Aldous Huxley', '', 'inglés', '288', 2, 2);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;

-- Volcando estructura para tabla biblioteca.prestamos
CREATE TABLE IF NOT EXISTS `prestamos` (
                                           `DNI_usuario` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                           `ISBN` varchar(13) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                           `fecha_salida` date NOT NULL,
                                           `fecha_devolucion` date DEFAULT NULL,
                                           KEY `FK_prestamos_usuarios` (`DNI_usuario`),
                                           KEY `FK_prestamos_libros` (`ISBN`),
                                           CONSTRAINT `FK_prestamos_libros` FOREIGN KEY (`ISBN`) REFERENCES `libros` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE,
                                           CONSTRAINT `FK_prestamos_usuarios` FOREIGN KEY (`DNI_usuario`) REFERENCES `usuarios` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla biblioteca.prestamos: ~0 rows (aproximadamente)
DELETE FROM `prestamos`;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;

-- Volcando estructura para tabla biblioteca.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
                                          `DNI` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                          `nombre` varchar(20) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                          `apellidos` varchar(40) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
                                          `domicilio` varchar(250) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
                                          `telefono` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
                                          `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
                                          `sancionado` tinyint(1) DEFAULT 0,
                                          `clave` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
                                          `fecha_Sancion` date DEFAULT NULL,
                                          PRIMARY KEY (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla biblioteca.usuarios: ~10 rows (aproximadamente)
DELETE FROM `usuarios`;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`DNI`, `nombre`, `apellidos`, `domicilio`, `telefono`, `email`, `sancionado`, `clave`, `fecha_Sancion`) VALUES
                                                                                                                                    ('06662539Z', 'Clara', 'Villegas García', 'C/ San Juan 22', '654121212', 'clara@gmail.com', 0, '1Aabcdef', NULL),
                                                                                                                                    ('18734202N', 'Sandra', 'Soriano Soriano', 'C/ Cerezo 56', '633040102', 'sandra@gmail.com', 0, '1Aabcdef', NULL),
                                                                                                                                    ('21591790B', 'Eva', 'López Márquez', 'C/ Sol 2', '656889977', 'eva@gmail.com', 0, '1Aabcdef', NULL),
                                                                                                                                    ('48858173D', 'Luis', 'Sánchez Ojeda', 'C/ Brenes 9', '646336699', 'luis@gmail.com', 0, '1Aabcdef', NULL),
                                                                                                                                    ('49193416G', 'José', 'Gutierrez Sánchez', 'C/ Manantiales 12', '633525252', 'jose@gmail.com', 0, '1Aabcdef', NULL),
                                                                                                                                    ('50809441A', 'Juan', 'Gutiérrez Oliva', 'C/ Estrella 23', '656448877', 'juan@gmail.com', 0, '1Aabcdef', NULL),
                                                                                                                                    ('65884856E', 'Ana', 'Ruíz Gómez', 'C/ Agua 5', '699332211', 'ana@gmail.com', 0, '1Aabcdef', NULL),
                                                                                                                                    ('78364372Z', 'Francisco', 'Rodriguez Espinosa', 'C/ Valdelagrana 44', '688447711', 'larry@gmail.com', 0, '1Aabcdef', NULL),
                                                                                                                                    ('80322781L', 'Daniel', 'López Clavijo', 'C/ Lirio 101', '666554411', 'daniel@gmail.com', 0, '1Aabcdef', NULL),
                                                                                                                                    ('90149903T', 'Andres', 'Castillo Portillo', 'C/ Luna 2', '686852852', 'andres@gmail.com', 0, '1Aabcdef', NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
