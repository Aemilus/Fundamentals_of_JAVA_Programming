SHOW WARNINGS;
CREATE USER IF NOT EXISTS 'agenda'@'%' IDENTIFIED BY 'agenda..';
GRANT ALL PRIVILEGES ON agenda_db.* TO 'agenda'@'%';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'agenda'@'%';
