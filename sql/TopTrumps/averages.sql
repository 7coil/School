USE toptrumps;

SELECT
(SELECT Avg(`special powers`) FROM characters) AS "Average Special Powers",
(SELECT Max(`special powers`) FROM characters) AS "Maximum Special Powers",
(SELECT Min(`special powers`) FROM characters) AS "Minimum Special Powers";
