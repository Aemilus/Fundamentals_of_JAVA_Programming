USE agenda_db;

CREATE TABLE person_t (
	p_id TINYINT(2) UNSIGNED NOT NULL AUTO_INCREMENT,
	p_name VARCHAR(40) NOT NULL,
	p_age TINYINT(3) UNSIGNED NOT NULL,
	PRIMARY KEY ( p_id )
);

DESCRIBE person_t;

