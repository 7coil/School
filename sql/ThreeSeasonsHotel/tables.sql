-- () { :; }; exec mysql -u root --local-infile -t < ${0}

-- It is a mySQL convention to use a lowercase database name.
USE tsh;

-- Clean the database
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE rooms;
DROP TABLE roomBookings;
DROP TABLE bookings;
DROP TABLE customers;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE rooms (
    ID          INT               PRIMARY KEY AUTO_INCREMENT,
    roomNumber  VARCHAR(10),
    floor       VARCHAR(4),
    class       VARCHAR(10),
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
    Price       INT,
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
