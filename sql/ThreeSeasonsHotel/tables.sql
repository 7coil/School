-- () { :; }; exec mysql -u root --local-infile -t < ${0}

-- It is a mySQL convention to use a lowercase database name.
USE tsh;

-- Clean the database
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS roombookings;
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS hotels;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE rooms (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    roomNumber  VARCHAR(10),
    floor       VARCHAR(4),
    cost        INT,
    occupancy   INT,
    singleBed   INT,
    doubleBed   INT,
    tripleBed   INT,
    queenBed    INT,
    kingBed     INT,
    twinBed     INT,
    ensuite     BOOLEAN,
    minibar     BOOLEAN,
    jacuzzi     BOOLEAN,
    seaView     BOOLEAN,
    family      BOOLEAN,
    honeyMoon   BOOLEAN,
    UNIQUE (roomNumber)
);

CREATE TABLE customers (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    Title       VARCHAR(6),
    Surname     VARCHAR(255),
    OtherNames  VARCHAR(255),
    Gender      INT, -- https://en.wikipedia.org/wiki/ISO/IEC_5218
    Telephone   VARCHAR(255),
    Street      VARCHAR(255),
    City        VARCHAR(255),
    Country     VARCHAR(2),
    PostZipCode VARCHAR(255)
);

CREATE TABLE bookings (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    CustomerID  INT,
    StartDate   DATETIME,
    EndDate     DATETIME,
    FOREIGN KEY (CustomerID) REFERENCES customers(ID)
);

CREATE TABLE roomBookings (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    RoomID      INT,
    BookingID   INT,
    FOREIGN KEY (RoomID) REFERENCES rooms(ID),
    FOREIGN KEY (BookingID) REFERENCES bookings(ID)
);

-- Import rooms
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('1', '1', '1', '11000', '2', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('2', '2', '1', '12000', '3', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('3', '3', '1', '10000', '2', '0', '1', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('4', '4', '1', '9000', '2', '0', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('5', '5', '2', '21000', '2', '0', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0', '1');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('6', '6', '2', '7000', '1', '1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('7', '7', '2', '11000', '2', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('8', '8', '2', '11000', '2', '0', '0', '0', '0', '0', '1', '0', '0', '1', '1', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('9', '9', '2', '10000', '4', '0', '1', '0', '0', '0', '1', '1', '0', '0', '0', '1', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('10', '10', '3', '9000', '2', '0', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('11', '11', '3', '8000', '1', '1', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('12', '12', '3', '9000', '2', '0', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('13', '13', '3', '11000', '2', '0', '1', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('14', '14', '3', '11000', '2', '0', '1', '0', '0', '0', '0', '1', '0', '0', '1', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('15', '15', '3', '11000', '2', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('16', '16', '4', '10000', '3', '1', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('17', '17', '4', '11000', '2', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '1', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('18', '18', '4', '11000', '2', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('19', '19', '4', '9000', '2', '0', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0');
INSERT INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES ('20', '20', '4', '11000', '2', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0');

-- Import customers
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('1', 'Sir', 'Hoy', 'Chris', '1', '08459811111', 'Lorem Avenue', 'ipSwitch', 'AE', '1123');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('2', 'Dr', 'Lennox', 'Richard Chris', '1', '01474112233', 'Church Walk', 'Gravesend', 'UK', 'DA122PR');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('3', 'Mrs', 'Claire', 'Judy Judith', '9', '07770382729', '28 Denton View', 'Fucking', 'AT', '5020');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('4', 'Mr', 'Kim', 'Jong Un', '1', '1234567890', "The People's Victory Road", 'Pyongyang', 'KP', '1');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('5', 'Miss', 'Eccles', 'Owphie', '2', '0987654321', 'Violent Child', 'Murder', 'KO', '78623');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('6', 'Mr', 'Amritpal', 'Singh', '1', '112358132134', 'Route 1', 'Goldenrod City', 'US', '90210');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('7', 'Miss', 'Runting', 'Tom', '2', '890478612398', 'London Road', 'Gravesend', 'UK', 'DA127HQ');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('8', 'Master', 'Pai', 'Ajit', '9', '31891401239', 'America Express', 'New York', 'US', '64391');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('9', 'Lord', 'Sugar', 'Alan', '1', '2478962', 'Commercial Road', 'Slough', 'UK', 'CB17SH');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('10', 'Prof', 'Poliakoff', 'Martyn', '1', '01159513520', 'Room B13a School of Chemistry', 'Nottingham', 'UK', 'NG72RD');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('11', 'APACHE', 'HELICOPTER', 'ATTACK', '0', '911911911', 'Presidential Close', 'Washington DC', 'US', '12345');
INSERT INTO `tsh`.`customers` (`ID`, `Title`, `Surname`, `OtherNames`, `Gender`, `Telephone`, `Street`, `City`, `Country`, `PostZipCode`) VALUES ('12', 'Queen', 'Elizabeth', 'Alexanda Mary', '2', '8931271297', '17 Bruton Street', 'Westminister', 'UK', 'L0ND0N');

-- Import some bookings
INSERT INTO `tsh`.`bookings` (`ID`, `CustomerID`, `StartDate`, `EndDate`) VALUES ('1', '12', '2018-05-03', '2018-06-04');
INSERT INTO `tsh`.`bookings` (`ID`, `CustomerID`, `StartDate`, `EndDate`) VALUES ('2', '2', '2002-01-01', '2002-01-17');
INSERT INTO `tsh`.`bookings` (`ID`, `CustomerID`, `StartDate`, `EndDate`) VALUES ('3', '5', '1992-08-23', '1992-08-25');

-- Insert some room bookings
INSERT INTO `tsh`.`roombookings` (`ID`, `RoomID`, `BookingID`) VALUES ('1', '17', '1');
INSERT INTO `tsh`.`roombookings` (`ID`, `RoomID`, `BookingID`) VALUES ('2', '18', '1');
INSERT INTO `tsh`.`roombookings` (`ID`, `RoomID`, `BookingID`) VALUES ('3', '2', '2');
