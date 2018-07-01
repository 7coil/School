-- () { :; }; exec mysql -u root --local-infile -t < ${0}

USE tsh;

LOAD DATA LOCAL INFILE 'customers.csv'
INTO TABLE customers
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA LOCAL INFILE 'rooms.csv'
INTO TABLE customers
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;
