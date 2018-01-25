-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 02, 2018 at 01:30 PM
-- Server version: 10.0.17-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flightbookingsystemv2`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `Id` int(11) NOT NULL,
  `NoOfAdults` int(11) NOT NULL,
  `NoOfChildren` int(11) NOT NULL,
  `NoOfInfants` int(11) NOT NULL,
  `Amount` decimal(6,2) NOT NULL,
  `ConfirmationNo` int(11) NOT NULL,
  `Customer_Id` int(11) NOT NULL,
  `OutboundFlight_Id` int(11) NOT NULL,
  `InboundFlight_Id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`Id`, `NoOfAdults`, `NoOfChildren`, `NoOfInfants`, `Amount`, `ConfirmationNo`, `Customer_Id`, `OutboundFlight_Id`, `InboundFlight_Id`) VALUES
(1, 2, 0, 0, '150.00', 987654321, 1, 1, 2),
(2, 12, 0, 0, '900.00', 876543219, 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Id` int(11) NOT NULL,
  `Title` varchar(10) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `Surname` varchar(45) NOT NULL,
  `MobileNumber` varchar(12) NOT NULL,
  `HomePhoneNumber` varchar(12) NOT NULL,
  `EmailAddress` varchar(45) NOT NULL,
  `LoginName` varchar(45) NOT NULL,
  `LoginPassword` varchar(45) NOT NULL,
  `CardType` varchar(45) NOT NULL,
  `CardNumber` varchar(16) NOT NULL,
  `CardExpiry` date NOT NULL,
  `AddressLine1` varchar(45) NOT NULL,
  `AddressLine2` varchar(45) NOT NULL,
  `PostCode` varchar(12) NOT NULL,
  `TownCity` varchar(45) NOT NULL,
  `CountyState` varchar(45) NOT NULL,
  `Country` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Id`, `Title`, `FirstName`, `Surname`, `MobileNumber`, `HomePhoneNumber`, `EmailAddress`, `LoginName`, `LoginPassword`, `CardType`, `CardNumber`, `CardExpiry`, `AddressLine1`, `AddressLine2`, `PostCode`, `TownCity`, `CountyState`, `Country`) VALUES
(1, 'mr', 'james', 'chalmers', '07552605450', '01343547869', 'james.chalmers184@gmail.com', 'jc184', '1Acheilidh1', 'VISA', '4111222233334444', '2019-09-09', '37 Forteath Avenue', 'west end', 'IV30 1TF', 'Elgin', 'Morayshire', 'UK');

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

CREATE TABLE `flight` (
  `Id` int(11) NOT NULL,
  `DepartureDateTime` datetime NOT NULL,
  `ArrivalDateTime` datetime NOT NULL,
  `FlightDate` date NOT NULL,
  `Route_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `flight`
--

INSERT INTO `flight` (`Id`, `DepartureDateTime`, `ArrivalDateTime`, `FlightDate`, `Route_Id`) VALUES
(1, '2017-12-12 12:00:00', '2018-12-12 12:45:00', '2017-12-12', 1),
(2, '2017-12-13 12:00:00', '2017-12-13 12:45:00', '2017-12-13', 1),
(3, '2017-12-14 12:00:00', '2017-12-14 12:45:00', '2017-12-14', 1),
(4, '2018-01-01 09:00:00', '2018-01-01 09:45:00', '2018-01-01', 1),
(5, '2018-01-01 14:00:00', '2018-01-01 14:45:00', '2018-01-01', 1),
(6, '2018-01-02 09:00:00', '2018-01-02 09:45:00', '2018-01-02', 1),
(7, '2018-01-02 14:00:00', '2018-01-02 14:45:00', '2018-01-02', 1),
(8, '2018-01-03 09:00:00', '2018-01-03 09:45:00', '2018-01-03', 1),
(9, '2018-01-03 14:00:00', '2018-01-03 14:45:00', '2018-01-03', 1),
(10, '2018-01-04 09:00:00', '2018-01-04 09:45:00', '2018-01-04', 1),
(11, '2018-01-04 14:00:00', '2018-01-04 14:45:00', '2018-01-04', 1),
(24, '2018-01-01 09:00:00', '2018-01-01 09:45:00', '2018-01-01', 2),
(25, '2018-01-01 14:00:00', '2018-01-01 14:45:00', '2018-01-01', 2),
(26, '2018-01-02 09:00:00', '2018-01-02 09:45:00', '2018-01-02', 2),
(27, '2018-01-02 14:00:00', '2018-01-02 14:45:00', '2018-01-02', 2),
(28, '2018-01-03 09:00:00', '2018-01-03 09:45:00', '2018-01-03', 2),
(29, '2018-01-03 14:00:00', '2018-01-03 14:45:00', '2018-01-03', 2),
(30, '2018-01-04 09:00:00', '2018-01-04 09:45:00', '2018-01-04', 2),
(31, '2018-01-04 14:00:00', '2018-01-04 14:45:00', '2018-01-04', 2);

-- --------------------------------------------------------

--
-- Table structure for table `passenger`
--

CREATE TABLE `passenger` (
  `Id` int(11) NOT NULL,
  `PassengerName` varchar(45) NOT NULL,
  `Booking_Id` int(11) NOT NULL,
  `BaggageItemId` int(11) NOT NULL,
  `BaggageItemWeightKg` int(11) NOT NULL,
  `Seat_SeatNo` int(11) NOT NULL,
  `Seat_Flight_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `route`
--

CREATE TABLE `route` (
  `Id` int(11) NOT NULL,
  `RouteName` varchar(45) NOT NULL,
  `AirportFrom` char(3) NOT NULL,
  `AirportTo` char(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `route`
--

INSERT INTO `route` (`Id`, `RouteName`, `AirportFrom`, `AirportTo`) VALUES
(1, 'Aberdeen to Kirkwall', 'ABD', 'KIR'),
(2, 'Kirkwall to Aberdeen', 'KIR', 'ABD');

-- --------------------------------------------------------

--
-- Table structure for table `seat`
--

CREATE TABLE `seat` (
  `SeatNo` int(11) NOT NULL,
  `Flight_Id` int(11) NOT NULL,
  `Booking_Id` int(11) NOT NULL,
  `SeatPrice` decimal(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `seat`
--

INSERT INTO `seat` (`SeatNo`, `Flight_Id`, `Booking_Id`, `SeatPrice`) VALUES
(0, 1, 1, '75.00'),
(0, 2, 2, '75.00'),
(0, 3, 2, '75.00'),
(1, 1, 1, '75.00'),
(1, 2, 2, '75.00'),
(1, 3, 2, '75.00'),
(2, 1, 1, '75.00'),
(2, 2, 1, '75.00'),
(2, 3, 2, '75.00'),
(3, 1, 1, '75.00'),
(3, 2, 2, '75.00'),
(5, 1, 1, '75.00'),
(5, 2, 1, '75.00'),
(6, 1, 1, '75.00'),
(6, 3, 2, '75.00'),
(7, 1, 1, '75.00'),
(8, 1, 1, '75.00'),
(8, 2, 1, '75.00'),
(9, 2, 2, '75.00'),
(10, 1, 1, '75.00'),
(11, 1, 1, '75.00'),
(11, 2, 2, '75.00'),
(11, 3, 2, '75.00'),
(12, 3, 2, '75.00'),
(16, 2, 1, '75.00'),
(16, 3, 2, '75.00'),
(17, 2, 1, '75.00'),
(23, 2, 1, '75.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_Booking_Customer1_idx` (`Customer_Id`),
  ADD KEY `fk_Booking_Flight1_idx` (`OutboundFlight_Id`),
  ADD KEY `fk_Booking_Flight2_idx` (`InboundFlight_Id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_Flight_Route1_idx` (`Route_Id`);

--
-- Indexes for table `passenger`
--
ALTER TABLE `passenger`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_Passenger_Booking1_idx` (`Booking_Id`),
  ADD KEY `fk_Passenger_Seat1_idx` (`Seat_SeatNo`,`Seat_Flight_Id`);

--
-- Indexes for table `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`SeatNo`,`Flight_Id`),
  ADD KEY `fk_Seat_Booking1_idx` (`Booking_Id`),
  ADD KEY `fk_Seat_Flight1_idx` (`Flight_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `flight`
--
ALTER TABLE `flight`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `passenger`
--
ALTER TABLE `passenger`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `route`
--
ALTER TABLE `route`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `fk_Booking_Customer1` FOREIGN KEY (`Customer_Id`) REFERENCES `customer` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Booking_Flight1` FOREIGN KEY (`OutboundFlight_Id`) REFERENCES `flight` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Booking_Flight2` FOREIGN KEY (`InboundFlight_Id`) REFERENCES `flight` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `flight`
--
ALTER TABLE `flight`
  ADD CONSTRAINT `fk_Flight_Route1` FOREIGN KEY (`Route_Id`) REFERENCES `route` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `passenger`
--
ALTER TABLE `passenger`
  ADD CONSTRAINT `fk_Passenger_Booking1` FOREIGN KEY (`Booking_Id`) REFERENCES `booking` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Passenger_Seat1` FOREIGN KEY (`Seat_SeatNo`,`Seat_Flight_Id`) REFERENCES `seat` (`SeatNo`, `Flight_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `seat`
--
ALTER TABLE `seat`
  ADD CONSTRAINT `fk_Seat_Booking1` FOREIGN KEY (`Booking_Id`) REFERENCES `booking` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Seat_Flight1` FOREIGN KEY (`Flight_Id`) REFERENCES `flight` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
