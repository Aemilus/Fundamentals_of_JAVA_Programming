SHOW WARNINGS;

/*Checking if user exists.*/
SELECT user, host FROM mysql.user WHERE user LIKE 'agenda';
SHOW GRANTS FOR 'agenda'@'%';

/*Drop user if exists.*/
DROP USER IF EXISTS 'agenda'@'%';
FLUSH PRIVILEGES;

/*Checking if user still exists.*/
SELECT user, host FROM mysql.user WHERE user LIKE 'agenda';

