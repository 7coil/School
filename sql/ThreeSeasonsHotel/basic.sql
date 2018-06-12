USE TSH;

-- Clean the database
DROP TABLE hotels;
DROP TABLE rooms;
DROP TABLE employees;
DROP TABLE roomBookings;
DROP TABLE bookings;
DROP TABLE customers;

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
    Floor       VARCHAR(3),
    Class       INT, -- Enum
    HotelID     INT
);

CREATE TABLE employees (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    Title       INT, -- Enum
    Surname     VARCHAR(255),
    OtherNames  VARCHAR(255),
    NationalInsurance   VARCHAR(10),
    Joined      DATE,
    Salary      INT
);

CREATE TABLE roomBookings (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    RoomID      INT,
    BookingID   INT
);

CREATE TABLE bookings (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    CustomerID  INT,
    Price       INT,
    Created     DATE
);

CREATE TABLE customers (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    Title       INT, -- Enum
    Surname     VARCHAR(255),
    OtherNames  VARCHAR(255),
    Telephone   VARCHAR(255),
    Street      VARCHAR(255),
    City        VARCHAR(255),
    Country     VARCHAR(2),
    PostZipCode VARCHAR(255)
);
