DROP DATABASE IF EXISTS app;
DROP ROLE IF EXISTS appadmin;

CREATE DATABASE app
    WITH 
    OWNER = sergei
    ENCODING = 'UTF8'
    LC_COLLATE = 'ru_RU.UTF-8'
    LC_CTYPE = 'ru_RU.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
\connect app

CREATE TABLE IF NOT EXISTS Theaters (
	theater_id SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL,
	address TEXT NOT NULL,
	email TEXT NOT NULL,
	phone TEXT NOT NULL,
	photo TEXT DEFAULT NULL,
	map TEXT DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Concert_halls (
	hall_id SERIAL PRIMARY KEY,
	theater_id INT REFERENCES Theaters ON DELETE CASCADE ON UPDATE CASCADE,
	"name" TEXT NOT NULL,
	scheme TEXT DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Type_of_seats (
	type_id SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Seats (
	seat_id SERIAL PRIMARY KEY,
	hall_id INT REFERENCES Concert_halls ON DELETE CASCADE ON UPDATE CASCADE,
	seat_type INT REFERENCES Type_of_seats ON DELETE CASCADE ON UPDATE CASCADE,
	"section" INT DEFAULT NULL,
	side CHAR CHECK (side IS NULL OR side = 'L' OR side = 'R'),
	"row" INT NOT NULL,
	seat INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Performances (
	performance_id SERIAL PRIMARY KEY,
	theater_id INT REFERENCES Theaters ON DELETE CASCADE ON UPDATE CASCADE,
	"name" TEXT NOT NULL,
	description TEXT NOT NULL,
	duration TIME NOT NULL,
	"start" DATE DEFAULT NULL,
	"finish" DATE DEFAULT NULL,
	poster TEXT DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Schedule (
	event_id SERIAL PRIMARY KEY,
	performance_id INT REFERENCES Performances ON DELETE CASCADE ON UPDATE CASCADE,
	hall_id INT REFERENCES Concert_halls ON DELETE CASCADE ON UPDATE CASCADE,
	"date" TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Tickets (
	ticket_id SERIAL PRIMARY KEY,
	event_id INT REFERENCES Schedule ON DELETE CASCADE ON UPDATE CASCADE,
	seat_id INT REFERENCES Seats ON DELETE CASCADE ON UPDATE CASCADE,
	"cost" NUMERIC(8,2) NOT NULL CHECK ("cost"::numeric > 0),
	in_stock BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS People (
	people_id SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL,
	description TEXT NOT NULL,
	photo TEXT DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Perf_persons (
	person_id SERIAL PRIMARY KEY,
	performance_id INT REFERENCES Performances ON DELETE CASCADE ON UPDATE CASCADE,
	people_id INT REFERENCES PEOPLE ON DELETE CASCADE ON UPDATE CASCADE,
	"role" TEXT NOT NULL
);

CREATE ROLE appadmin WITH LOGIN PASSWORD '123456';
GRANT CONNECT ON DATABASE app TO appadmin;
GRANT ALL ON ALL TABLES IN SCHEMA public TO appadmin; 
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO appadmin;
