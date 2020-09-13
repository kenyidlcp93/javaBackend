-- init load table person

INSERT INTO BCP.PERSON(person_id, first_name, last_name, is_active)
  VALUES('P0001','Christian','Valdez', 1);
INSERT INTO BCP.PERSON(person_id, first_name, last_name, is_active)
  VALUES('P0002','Carolina','Paivva', 1);
INSERT INTO BCP.PERSON(person_id, first_name, last_name, is_active)
  VALUES('P0003','Stephanie','Calderon', 1);

-- init load table user
INSERT INTO BCP.[USER](user_id, email, passwd, person_id, is_active)
  VALUES('U0001', 'cvaldez@outlook.com','1234','P0001',1);
INSERT INTO BCP.[USER](user_id, email, passwd, person_id, is_active)
  VALUES('U0002', 'scalderon@outlook.com','1234','P0003',1);

-- init load table bike
INSERT INTO BCP.BIKE(bike_id, [type], brand, price_by_minute, is_active)
  VALUES('K0001', 'Urban', 'Grand', 0.525, 1);
INSERT INTO BCP.BIKE(bike_id, [type], brand, price_by_minute, is_active)
  VALUES('K0002', 'Running', 'Grand', 1.452, 1);
INSERT INTO BCP.BIKE(bike_id, [type], brand, price_by_minute, is_active)
  VALUES('K0003', 'TriCycle', 'Grand', 0.8523, 1);
INSERT INTO BCP.BIKE(bike_id, [type], brand, price_by_minute, is_active)
  VALUES('K0004', 'Electric Bike', 'Grand', 2.525, 1);

-- init load table booking
INSERT INTO BCP.BOOKING(booking_id, is_canceled, created_at, user_id, bike_id, is_completed)
  VALUES(1,0,convert(datetime, '2020-03-18 00:00:00'), 'U0001', 'K0001', 0);
INSERT INTO BCP.BOOKING(booking_id, is_canceled, created_at, user_id, bike_id, is_completed)
  VALUES(2,0,convert(datetime, '2020-03-18 00:00:00'), 'U0001', 'K0001', 0);
INSERT INTO BCP.BOOKING(booking_id, is_canceled, created_at, user_id, bike_id, is_completed)
  VALUES(3,0,convert(datetime, '2020-03-18 00:00:00'),'U0002', 'K0002', 0);
INSERT INTO BCP.BOOKING(booking_id, is_canceled, created_at, user_id, bike_id, date_completed, is_completed)
  VALUES(4,1,convert(datetime, '2020-03-18 00:00:00'),'U0002', 'K0004', convert(datetime, '2020-03-20 00:00:00'), 1);

-- init load table station
INSERT INTO BCP.STATION(station_id, name, location, is_active)
  VALUES('S0001','Station Central','15.21554 -15.254842', 1);
INSERT INTO BCP.STATION(station_id, name, location, is_active)
  VALUES('S0002','Central Park','43.21554 -16.254842', 1);
INSERT INTO BCP.STATION(station_id, name, location, is_active)
  VALUES('S0003','Aranburú','78.21554 98.254842', 1);
INSERT INTO BCP.STATION(station_id, name, location, is_active)
  VALUES('S0004','Rizzo','13.21554 345.254842', 1);
INSERT INTO BCP.STATION(station_id, name, location, is_active)
  VALUES('S0005','Los Geranios','56.21554 23.254842', 1);


-- init load table Booking_details
INSERT INTO BCP.Booking_details(booking_id, origin_station_id, destination_station_id, start_date, end_date)
  VALUES(1, 'S0001', 'S0003', convert(datetime, '2020-03-21 00:00:00'), null);
INSERT INTO BCP.Booking_details(booking_id, origin_station_id, destination_station_id, start_date, end_date)
  VALUES(2, 'S0002', 'S0003', convert(datetime, '2020-03-21 00:00:00'), null);
INSERT INTO BCP.Booking_details(booking_id, origin_station_id, destination_station_id, start_date, end_date)
  VALUES(3, 'S0003', 'S0001', convert(datetime, '2020-03-21 00:00:00'), null);
INSERT INTO BCP.Booking_details(booking_id, origin_station_id, destination_station_id, start_date, end_date)
  VALUES(4, 'S0003', 'S0005', convert(datetime, '2020-03-18 00:00:00'), convert(datetime, '2020-03-20 00:00:00'));


-- init load table RentalDetails
INSERT INTO BCP.RENTALDETAILS(booking_id, origin_station_id, destination_station_id, start_date, end_date)
  VALUES(1, 'S0001', 'S0003', convert(datetime, '2020-03-21 00:00:00'), null);
INSERT INTO BCP.RENTALDETAILS(booking_id, origin_station_id, destination_station_id, start_date, end_date)
  VALUES(2, 'S0002', 'S0003', convert(datetime, '2020-03-21 00:00:00'), null);
INSERT INTO BCP.RENTALDETAILS(booking_id, origin_station_id, destination_station_id, start_date, end_date)
  VALUES(3, 'S0003', 'S0001', convert(datetime, '2020-03-21 00:00:00'), null);
INSERT INTO BCP.RENTALDETAILS(booking_id, origin_station_id, destination_station_id, start_date, end_date)
  VALUES(4, 'S0003', 'S0005', convert(datetime, '2020-03-21 00:00:00'), null);

-- init load  table DetailStations
INSERT INTO BCP.DETAIL_STATIONS(station_id,bike_id,quantity,is_active)
  VALUES('S0001', 'K0001', 10, 1);
INSERT INTO BCP.DETAIL_STATIONS(station_id,bike_id,quantity,is_active)
    VALUES('S0001', 'K0003', 8, 1);
INSERT INTO BCP.DETAIL_STATIONS(station_id,bike_id,quantity,is_active)
  VALUES('S0002', 'K0001', 50, 1);
INSERT INTO BCP.DETAIL_STATIONS(station_id,bike_id,quantity,is_active)
  VALUES('S0003', 'K0003', 30, 1);
INSERT INTO BCP.DETAIL_STATIONS(station_id,bike_id,quantity,is_active)
  VALUES('S0004', 'K0002', 20, 1);
INSERT INTO BCP.DETAIL_STATIONS(station_id,bike_id,quantity,is_active)
  VALUES('S0005', 'K0004', 5, 1);

SET IDENTITY_INSERT BCP.Booking OFF;