-- drop database if exists TrainApp;
-- create database if not exists TrainApp;

use TrainApp;

-- -- User account tables

-- create table Customer (
-- 	userName varchar(50) primary key, 
-- 	password varchar(50), 
-- 	email varchar(100),
-- 	firstName varchar(30), 
-- 	lastName varchar(30),
-- 	isChild boolean, 
-- 	isSenior boolean,
-- 	isDisabled boolean, 
-- 	discount float
-- );
-- insert Customer values
-- ('customer1', '123abc', 'email@email', 'name', 'name', 0, 0, 0, 0),
-- ('customer2', '123abc', 'email@email', 'name', 'name', 0, 0, 0, 0);

-- create table Employee(
-- 	SSN varchar(11),
--     userName varchar(50) primary key,
--     password varchar(50),
--     firstName varchar(30),
--     lastName varchar(30)
-- );
-- insert Employee values
-- ('123-45-6789', 'employee1', '123abc', 'name', 'name'),
-- ('123-45-6789', 'employee2', '123abc', 'name', 'name');

-- create table Admin(
-- 	password varchar(50)
-- );
-- insert into Admin values ('password');

-- create table SupportTicket(
-- 	supportTicketID int primary key AUTO_INCREMENT,
--     userName varchar(50),
--     title varchar(50),
--     body varchar(5000),
--     foreign key (userName) references Customer (userName)
-- );

-- create table SupportTicketMessage(
-- 	messageID int primary key AUTO_INCREMENT,
--     supportTicketID int,
--     userNameCustomer varchar(50),
--     userNameEmployee varchar(50),
--     body varchar(5000),
--     foreign key (supportTicketID) references SupportTicket (supportTicketID) on delete cascade,
--     foreign key (userNameCustomer) references Customer (userName) on delete cascade,
--     foreign key (userNameEmployee) references Employee (userName) on delete cascade
-- );

-- -- Train Schedule tables

-- create table Station (
--   stationID int,
--   name varchar(100),
--   city varchar(50),
--   state varchar(50),
--   primary key (stationID)
-- );

-- insert into Station (stationID, name, city, state) values 
-- (1, 'A', 'APLHA', ' NEW JERSEY'),
-- (2, 'B', 'BETA', ' NEW JERSEY'),
-- (3, 'C', 'CHARLIE', 'NEW YORK'),
-- (4, 'D', 'DELTA', 'NEW YORK'),
-- (5, 'E', 'ECHO', 'NEW YORK'),
-- (6, 'F', 'FOXTROT', ' NEW JERSEY'),
-- (7, 'G', 'GAMMA', ' NEW JERSEY'),
-- (8, 'H', 'HOTEL', ' NEW JERSEY'),
-- (9, 'I', 'INDIA', ' NEW JERSEY'),
-- (10, 'J', 'JUILET', 'NEW YORK'),
-- (11, 'K', 'APLHA', 'NEW YORK'),
-- (12, 'L', 'BETA', 'NEW YORK'),
-- (13, 'M', 'CHARLIE', 'NEW YORK'),
-- (14, 'N', 'DELTA', 'NEW YORK'),
-- (15, 'O', 'ECHO', 'NEW YORK'),
-- (16, 'P', 'FOXFROT', ' NEW JERSEY'),
-- (17, 'Q', 'GAMMA', ' NEW JERSEY'),
-- (18, 'R', 'HOTEL', ' NEW JERSEY'),
-- (19, 'S', 'INDIA', ' NEW JERSEY'),
-- (20, 'T', 'JUILET', 'NEW YORK');

-- create table Train (
--   tid int,
--   primary key (tid)
-- );
-- insert Train VALUES 
-- (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

-- create table TrainSchedule(
-- 	transitLine varchar(50) primary key,
-- 	depTime datetime,
-- 	arrivalTime datetime,
-- 	originStation int,
-- 	desStation int,
-- 	fare float,
-- 	tid int,
-- 	foreign key(originStation) references Station(stationID),
-- 	foreign key(desStation) references Station(stationID),
-- 	foreign key(tid) references Train(tid) 
-- );

-- insert into TrainSchedule (transitLine, depTime, arrivalTime, originStation, desStation, fare, tid) values
-- ('AB', '2020-12-01 12:30:00', '2020-12-01 13:00:00', 1, 2, 50, 1),
-- ('ABC', '2020-12-01 13:30:00', '2020-12-01 15:00:00', 1, 3, 60, 2),
-- ('DCB', '2020-12-01 23:00:00', '2020-12-02 01:00:00', 5, 2, 40, 3),
-- ('JC', '2020-12-01 15:00:00', '2020-12-02 08:00:00', 10, 3, 100, 4),
-- ('CD', '2020-12-02 12:30:00', '2020-12-02 13:30:00', 3, 4, 20, 1);

-- create table Stop(
-- 	transitLine varchar(50),
-- 	stationID int,
-- 	departTime datetime,
-- 	arrivalTime datetime,
--     primary key(transitLine, stationID),
-- 	foreign key(transitLine) references TrainSchedule(transitLine),
-- 	foreign Key(stationID) references Station(stationID)
-- );

-- insert into Stop values 
-- ('AB', 1, '2020-12-01 12:30:00', '2020-12-01 12:20:00'),
-- ('AB', 2, '2020-12-01 13:10:00', '2020-12-01 13:00:00'),
-- ('ABC', 1, '2020-12-01 13:30:00', '2020-12-01 13:20:30'),
-- ('ABC', 2, '2020-12-01 14:10:00', '2020-12-01 14:00:00'),
-- ('ABC', 3, '2020-12-01 15:10:00', '2020-12-01 15:00:00'),
-- ('DCB', 4, '2020-12-01 23:00:00', '2020-12-01 22:50:00'),
-- ('DCB', 3, '2020-12-02 00:10:00', '2020-12-02 00:00:00'),
-- ('DCB', 2, '2020-12-02 01:10:00', '2020-12-02 01:00:00'),
-- ('JC', 10, '2020-12-01 15:00:00', '2020-12-01 14:40:00'),
-- ('JC', 9, '2020-12-01 17:00:00', '2020-12-01 16:40:00'),
-- ('JC', 8, '2020-12-01 21:00:00', '2020-12-01 20:30:00'),
-- ('JC', 7, '2020-12-02 03:00:00', '2020-12-02 02:30:00'),
-- ('JC', 6, '2020-12-02 05:20:00', '2020-12-02 05:00:00'),
-- ('JC', 5, '2020-12-02 06:10:00', '2020-12-02 06:00:00'),
-- ('JC', 4, '2020-12-02 07:10:00', '2020-12-02 07:00:00'),
-- ('JC', 3, '2020-12-02 08:10:00', '2020-12-02 08:00:00'),
-- ('CD', 4, '2020-12-02 12:30:00', '2020-12-02 12:20:00'),
-- ('CD', 3, '2020-12-02 13:40:00', '2020-12-02 13:30:00');

-- create table Reservation(
-- 	reservationID int primary key AUTO_INCREMENT,
--     userName varchar(50),
--     transitLine varchar(50),
--     origin varchar(50),
--     destination varchar(50),
--     departureTime datetime,
--     arrivalTime varchar(50),
--     roundTrip boolean,
--     price float,
--     current boolean,
--     foreign key (userName) references Customer (userName),
--     foreign key (transitLine) references TrainSchedule (transitLine)
-- );
