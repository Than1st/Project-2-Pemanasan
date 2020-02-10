-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 10, 2020 at 07:03 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coba`
--

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `nim` int(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jk` enum('L','P') NOT NULL,
  `alamat` text NOT NULL,
  `peminatan` varchar(30) NOT NULL,
  `agama` enum('islam','katolik','protestan','budha','hindu','konghucu') NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`nim`, `nama`, `jk`, `alamat`, `peminatan`, `agama`, `email`) VALUES
(1912500942, 'Daffa Febri Nugroho', 'L', 'Meruya', 'Pemrograman', 'islam', '1912500942@student.budiluhur.ac.id'),
(1912500943, 'Muhammad Ajie Panca Kurniawan', 'L', 'Jl. Makam Pari 2', 'Pemrograman', 'islam', '1912500943@student.budiluhur.ac.id'),
(1912500944, 'Armand Pramudia', '', 'Jakarta', '', 'islam', '1911500518@student.budiluhur.ac.id'),
(1912500945, 'Dioda Zenner ', '', '1', '', 'islam', ''),
(1912500946, 'Benita Hasna Raissa', '', '22', '', 'islam', ''),
(1912500948, 'Nisrina Megaputri Yoniton', '', '333', '', 'islam', ''),
(1912500949, 'Ibnu Ramadhan Arbiansyah', '', '333', '', 'islam', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`nim`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
