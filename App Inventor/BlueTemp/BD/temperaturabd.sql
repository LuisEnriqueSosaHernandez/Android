-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-12-2017 a las 02:31:39
-- Versión del servidor: 10.1.22-MariaDB
-- Versión de PHP: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `temperaturabd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos`
--

CREATE TABLE `datos` (
  `id` int(11) NOT NULL,
  `temperatura` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `nomenclatura` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `datos`
--

INSERT INTO `datos` (`id`, `temperatura`, `nomenclatura`, `fecha`, `hora`) VALUES
(1, '\r70.38 \n', 'Â°C', NULL, NULL),
(2, '\r70.38 \n', 'Â°C', NULL, NULL),
(3, '\r70.86 \n', 'Â°C', NULL, NULL),
(4, '\r70.86 \n', 'Â°C', NULL, NULL),
(5, '\r70.86 \n', 'Â°C', NULL, NULL),
(6, '\r70.38 \n', 'Â°C', NULL, NULL),
(7, '\r70.86 \n', 'Â°C', NULL, NULL),
(8, '\r71.35 \n', 'Â°C', NULL, NULL),
(9, '\r70.38 \n', 'Â°C', NULL, NULL),
(10, '', 'Â°C', NULL, NULL),
(11, '70', 'C', '0000-00-00', '05:01:10'),
(12, '70', 'C', '2017-07-16', '05:01:10'),
(13, '\r68.42 \n', 'Â°C$fecha=2017-12-07$hora=05:04:35', '0000-00-00', '00:00:00'),
(14, '', 'Â°C$fecha=2017-12-07$hora=05:05:14', '0000-00-00', '00:00:00'),
(15, '\r69.89 \n', 'Â°C$fecha=2017-12-07$hora=05:05:25', '0000-00-00', '00:00:00'),
(16, '\r73.31 \n', 'Â°C$fecha=2017-12-07$hora=05:05:35', '0000-00-00', '00:00:00'),
(17, '\r71.35 \n', 'Â°C$fecha=2017-12-07$hora=05:05:45', '0000-00-00', '00:00:00'),
(18, '\r71.84 \n', 'Â°C$fecha=2017-12-07$hora=05:05:55', '0000-00-00', '00:00:00'),
(19, '\r70.38 \n', 'Â°C$fecha=2017-12-07$hora=05:06:05', '0000-00-00', '00:00:00'),
(20, '\r71.35 \n', 'Â°C$fecha=2017-12-07$hora=05:06:15', '0000-00-00', '00:00:00'),
(21, '\r66.47 \n', 'Â°C$fecha=2017-12-07$hora=05:06:25', '0000-00-00', '00:00:00'),
(22, '\r70.86 \n', 'Â°C$fecha=2017-12-07$hora=05:06:35', '0000-00-00', '00:00:00'),
(23, '\r72.33 \n', 'Â°C$fecha=2017-12-07$hora=05:06:45', '0000-00-00', '00:00:00'),
(24, '\r71.84 \n', 'Â°C$fecha=2017-12-07$hora=05:06:55', '0000-00-00', '00:00:00'),
(25, '\r69.89 \n', 'Â°C$fecha=2017-12-07$hora=05:07:05', '0000-00-00', '00:00:00'),
(26, '\r71.35 \n', 'Â°C', '2017-12-07', '05:07:15'),
(27, '\r71.35 \n', 'Â°C', '2017-12-07', '05:15:15'),
(28, '\r70.38 \n', 'Â°C', '2017-12-07', '05:15:25'),
(29, '\r71.35 \n', 'Â°C', '2017-12-07', '05:15:36'),
(30, '\r74.29 \n', 'Â°C', '2017-12-07', '05:15:46'),
(31, '\r66.47 \n', 'Â°C', '2017-12-07', '05:15:56'),
(32, '\r70.38 \n', 'Â°C', '2017-12-07', '05:16:05'),
(33, '\r70.86 \n', 'Â°C', '2017-12-07', '05:16:16'),
(34, '\r70.38 \n', 'Â°C', '2017-12-07', '05:16:26'),
(35, '\r70.86 \n', 'Â°C', '2017-12-07', '05:16:36'),
(36, '\r71.35 \n', 'Â°C', '2017-12-07', '05:16:46'),
(37, '\r70.86 \n', 'Â°C', '2017-12-07', '05:16:55'),
(38, '\r69.40 \n', 'Â°C', '2017-12-07', '05:17:06'),
(39, '\r70.38 \n', 'Â°C', '2017-12-07', '05:17:16'),
(40, '\r70.86 \n', 'Â°C', '2017-12-07', '05:17:26'),
(41, '\r71.35 \n', 'Â°C', '2017-12-07', '05:17:36'),
(42, '\r71.35 \n', 'Â°C', '2017-12-07', '05:17:45'),
(43, '\r70.86 \n', 'Â°C', '2017-12-07', '05:17:56'),
(44, '\r70.86 \n', 'Â°C', '2017-12-07', '05:18:06'),
(45, '\r70.86 \n', 'Â°C', '2017-12-07', '05:18:16'),
(46, '\r70.38 \n', 'Â°C', '2017-12-07', '05:18:26'),
(47, '\r71.35 \n', 'Â°C', '2017-12-07', '05:18:36'),
(48, '70', 'C', '2017-08-16', '05:01:10'),
(49, '70', 'C', '2017-09-16', '05:01:10'),
(50, '70', 'C', '2017-08-12', '05:01:10'),
(51, '70', 'C', '2017-09-12', '05:01:10'),
(52, '70', 'C', '2017-12-08', '05:01:10'),
(53, '70', 'C', '2017-12-09', '05:01:10');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `datos`
--
ALTER TABLE `datos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `datos`
--
ALTER TABLE `datos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
