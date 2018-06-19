-- Use the correct database
USE toptrumps;

-- Get the maximum, minimum and average special powers
SELECT
(SELECT Avg(`special powers`) FROM characters) AS "Average Special Powers",
(SELECT Max(`special powers`) FROM characters) AS "Maximum Special Powers",
(SELECT Min(`special powers`) FROM characters) AS "Minimum Special Powers";

-- Get the character with the lowest special powers
SELECT * FROM characters ORDER BY `special powers` ASC LIMIT 1;

-- Get the Villain with the highest special powers
SELECT * FROM characters WHERE side LIKE "Villain" ORDER BY `special powers` DESC LIMIT 1;

-- Get all characters who debuted in a year
SELECT * FROM characters WHERE `debut year` < 1960;

-- Get the number of characters
SELECT
(SELECT SUM(cunning) FROM characters WHERE side LIKE "Villain") AS "Villain Cunning",
(SELECT SUM(cunning) FROM characters WHERE side NOT LIKE "Villain") AS "Hero Cunning";
