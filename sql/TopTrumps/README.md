
# Common Mistakes:
Students did not realise that the CSV headers can be different to the field names.
Field names should not have spaces in them. This is often done for the `debut year` field.
A field cannot be named `Character`, as it is a data type. You could use `CharacterName` instead.
The character ID field must have AUTO_INCREMENT and NOT NULL, otherwise the data will not be imported correctly.

# Top Trumps Guide:
- Download characters.csv and make a file called TopTrumps.sql in a folder in cloud9.
- In the TopTrumps SQL file, you must:
   - Create a database called TopTrumps.
   - Tell MySQL that you want to use the database called TopTrumps.
   - Create a table called Characters with all the headings of the csv file.
   - This will also need to have the correct data-types to be able to import from the CSV file.
   - Import the CSV file using:
      ```sql
      LOAD DATA LOCAL INFILE 'characters.csv'
      INTO TABLE Characters
      FIELDS TERMINATED BY ','
      LINES TERMINATED BY '\n'
      IGNORE 1 ROWS;
      ```
    - Use `EXPLAIN Characters;` to check that the table was created correctly.
    - Use `SELECT * FROM Characters;` to check the data.
    - Some users may require the removal of the `LOCAL` inside the SQL.
- Right click the folder you created, and click Open Terminal Here.
- Type `mysql-ctl start` to start the database server.
- Type `mysql -u root --local-infile` to open the MySQL console.
- Type `source TopTrumps.sql` to run your SQL commands.

If you have any questions, please add them here:
