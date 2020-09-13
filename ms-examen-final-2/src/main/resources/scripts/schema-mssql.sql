DROP TABLE IF EXISTS BCP.Booking_details;
DROP TABLE IF EXISTS BCP.Detail_Stations;
DROP TABLE IF EXISTS BCP.rental_details;
DROP TABLE IF EXISTS BCP.RentalDetails;
DROP TABLE IF EXISTS BCP.Station;
DROP TABLE IF EXISTS BCP.Booking;
DROP TABLE IF EXISTS BCP.Bike;
DROP TABLE IF EXISTS BCP.[User];
DROP TABLE IF EXISTS BCP.Person;

-- DROP SCHEMA IF EXISTS [BCP];

IF NOT EXISTS (SELECT 0 FROM sys.schemas WHERE name = 'BCP')
  BEGIN
    EXECUTE ('CREATE SCHEMA BCP')
  END;

CREATE TABLE BCP.Person (
    person_id VARCHAR(5) NOT NULL,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    is_active BIT NOT NULL,
    PRIMARY KEY (person_id)
);

CREATE TABLE BCP.[User] (
    [user_id] VARCHAR(5) NOT NULL,
    email VARCHAR(50) NOT NULL,
    passwd VARCHAR(15) NOT NULL,
    person_id VARCHAR(5) NOT NULL,
    is_active BIT NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (email)
);

CREATE TABLE BCP.Booking (
    booking_id int IDENTITY(1,1) NOT NULL,
    is_canceled BIT NOT NULL,
    created_at DATETIME NOT NULL,
    bike_id VARCHAR(5) NOT NULL,
    is_completed BIT NULL,
    user_id VARCHAR(5) NOT NULL,
    date_completed DATETIME NULL
    PRIMARY KEY (booking_id)
);

CREATE TABLE BCP.Booking_details (
    booking_id INT NOT NULL,
    origin_station_id VARCHAR(5) NOT NULL,
    destination_station_id VARCHAR(5) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME
);

CREATE TABLE BCP.Station (
    station_id VARCHAR(5) NOT NULL,
    name VARCHAR(60) NOT NULL,
    location VARCHAR(50) NOT NULL,
    is_active BIT NOT NULL,
    PRIMARY KEY (station_id)
);

CREATE TABLE BCP.Bike (
    bike_id VARCHAR(5) NOT NULL,
    type VARCHAR(30) NOT NULL,
    brand VARCHAR(20) NOT NULL,
    price_by_minute DECIMAL NOT NULL,
    is_active BIT NOT NULL,
    PRIMARY KEY (bike_id)
);

CREATE TABLE BCP.RentalDetails (
    booking_id INT NOT NULL,
    origin_station_id VARCHAR(5) NOT NULL,
    destination_station_id VARCHAR(5) NOT NULL,
    start_date datetime NOT NULL,
    end_date datetime
);

CREATE TABLE BCP.Detail_Stations (
    station_id VARCHAR(5) NOT NULL,
    bike_id VARCHAR(5) NOT NULL,
    quantity INT NOT NULL,
    is_active BIT NOT NULL
);

ALTER TABLE [BCP].[User]
  ADD FOREIGN KEY (person_id) REFERENCES [BCP].Person(person_id);
ALTER TABLE [BCP].Booking
  ADD FOREIGN KEY (user_id) REFERENCES [BCP].[User](user_id);
ALTER TABLE [BCP].Booking
  ADD FOREIGN KEY (bike_id) REFERENCES [BCP].Bike(bike_id);
ALTER TABLE [BCP].RentalDetails
  ADD FOREIGN KEY (booking_id) REFERENCES [BCP].Booking(booking_id);
ALTER TABLE [BCP].RentalDetails
  ADD FOREIGN KEY (origin_station_id) REFERENCES [BCP].Station(station_id);
ALTER TABLE [BCP].RentalDetails
  ADD FOREIGN KEY (destination_station_id) REFERENCES [BCP].Station(station_id);
ALTER TABLE [BCP].Detail_Stations
  ADD FOREIGN KEY (station_id) REFERENCES [BCP].Station(station_id);
ALTER TABLE [BCP].Detail_Stations
  ADD FOREIGN KEY (bike_id) REFERENCES [BCP].Bike(bike_id);
ALTER TABLE [BCP].Booking_details
  ADD FOREIGN KEY (booking_id) REFERENCES [BCP].Booking(booking_id);
ALTER TABLE [BCP].Booking_details
  ADD FOREIGN KEY (origin_station_id) REFERENCES [BCP].Station(station_id);
ALTER TABLE [BCP].Booking_details
  ADD FOREIGN KEY (destination_station_id) REFERENCES [BCP].Station(station_id);

SET IDENTITY_INSERT BCP.Booking ON;