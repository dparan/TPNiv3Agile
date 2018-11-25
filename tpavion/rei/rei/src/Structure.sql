-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : tpavion2.cuxgpqum2bql.us-east-2.rds.amazonaws.com:3306
-- Généré le :  sam. 10 nov. 2018 à 18:00
-- Version du serveur :  5.6.41-log
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `tpavion`
--

-- --------------------------------------------------------

--
-- Structure de la table `DepartAvion`
--

DROP TABLE IF EXISTS `DepartAvion`;
CREATE TABLE IF NOT EXISTS `DepartAvion` (
  `id` int(11) NOT NULL,
  `dateDepart` date NOT NULL,
  `immatriculation` varchar(255) NOT NULL,
  `qteCarburant` int(11) NOT NULL,
  PRIMARY KEY (`id`,`dateDepart`,`immatriculation`),
  KEY `immatriculation` (`immatriculation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `DepartAvion`
--
ALTER TABLE `DepartAvion`
  ADD CONSTRAINT `DepartAvion_ibfk_1` FOREIGN KEY (`id`,`dateDepart`) REFERENCES `Depart` (`id`, `dateDepart`),
  ADD CONSTRAINT `DepartAvion_ibfk_2` FOREIGN KEY (`immatriculation`) REFERENCES `Avion` (`immatriculation`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
