-- () { :; }; exec mysql -u root --local-infile -t < ${0}

-- It is a mySQL convention to use a lowercase database name.
USE tsh;

-- Clean the database
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE hotels;
DROP TABLE rooms;
DROP TABLE employees;
DROP TABLE roomBookings;
DROP TABLE bookings;
DROP TABLE customers;
SET FOREIGN_KEY_CHECKS = 1;

-- Create tables
CREATE TABLE hotels (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    Street      TEXT,
    City        TEXT,
    Country     TEXT,
    PostZipCode VARCHAR(10)
);

CREATE TABLE rooms (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    HotelID     INT,
    Floor       VARCHAR(3),
    Class       VARCHAR(10),
    FOREIGN KEY (HotelID) REFERENCES hotels(ID)
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
    Price       INT,
    StartDate   DATE,
    EndDate     DATE,
    FOREIGN KEY (CustomerID) REFERENCES customers(ID)
);

CREATE TABLE roomBookings (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    RoomID      INT,
    BookingID   INT,
    FOREIGN KEY (RoomID) REFERENCES rooms(ID),
    FOREIGN KEY (BookingID) REFERENCES bookings(ID)
);
