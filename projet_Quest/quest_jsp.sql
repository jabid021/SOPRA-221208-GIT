-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 02 déc. 2022 à 23:43
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `quest_jsp`
--
CREATE DATABASE IF NOT EXISTS `quest_jsp` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `quest_jsp`;

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

DROP TABLE IF EXISTS `filiere`;
CREATE TABLE IF NOT EXISTS `filiere` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  `debut` date NOT NULL,
  `fin` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `filiere`
--

INSERT INTO `filiere` (`id`, `libelle`, `debut`, `fin`) VALUES
(1, 'DIS-399-SOPRA-JAVA	', '2022-10-25', '2023-01-20');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
CREATE TABLE IF NOT EXISTS `matiere` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(25) NOT NULL,
  `quest` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`id`, `libelle`, `quest`) VALUES
(1, 'Algo avec Java', 7245),
(2, 'SQL', 4352);

-- --------------------------------------------------------

--
-- Structure de la table `ordinateur`
--

DROP TABLE IF EXISTS `ordinateur`;
CREATE TABLE IF NOT EXISTS `ordinateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marque` varchar(15) NOT NULL,
  `ram` int(11) NOT NULL,
  `stagiaire` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ordinateur_stagiaire` (`stagiaire`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ordinateur`
--

INSERT INTO `ordinateur` (`id`, `marque`, `ram`, `stagiaire`) VALUES
(1, 'Asus', 8, 1),
(2, 'Dell', 16, 2);

-- --------------------------------------------------------

--
-- Structure de la table `stagiaire`
--

DROP TABLE IF EXISTS `stagiaire`;
CREATE TABLE IF NOT EXISTS `stagiaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `filiere` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stagiaire_filiere` (`filiere`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `stagiaire`
--

INSERT INTO `stagiaire` (`id`, `nom`, `prenom`, `email`, `filiere`) VALUES
(1, 'Abid', 'Jordan', 'jordanabid@gmail.com', 1),
(2, 'Doe', 'John', 'jdoe@gmail.com	', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ordinateur`
--
ALTER TABLE `ordinateur`
  ADD CONSTRAINT `fk_ordinateur_stagiaire` FOREIGN KEY (`stagiaire`) REFERENCES `stagiaire` (`id`);

--
-- Contraintes pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD CONSTRAINT `fk_stagiaire_filiere` FOREIGN KEY (`filiere`) REFERENCES `filiere` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
