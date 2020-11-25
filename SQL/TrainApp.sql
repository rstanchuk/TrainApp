-- drop database if exists TrainApp;
-- create database if not exists TrainApp;

use TrainApp;

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

-- create table Employee(
-- 	SSN varchar(11),
--     userName varchar(50) primary key,
--     password varchar(50),
--     firstName varchar(30),
--     lastName varchar(30)
-- );

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

-- insert into SupportTicket (userName, title, body) values
-- ('roman23', 'I have a problem', 'esdcdsrcvdrvdrvrdvr'),
-- ('roman23', 'I have a problem2', 'esdcdsrcvdrvdrvrdvr');

-- select * from SupportTicket where userName='roman23';
select count(*) from SupportTicket where userName='roman23';

-- delete from SupportTicket
-- where userName = 'roman23';

-- insert into SupportTicketMessage (supportTicketID, userNameCustomer, body) values
-- (7, 'roman23', 'dfvdfvfdvfdvf'),
-- (8, 'roman23', 'dfvdfvfddsvfdvvdfvfdvf');

-- insert into SupportTicketMessage (supportTicketID, userNameEmployee, body) values
-- (7, 'roman45', 'dfvdfvfdvfdvf'),
-- (8, 'roman45', 'dfvdfvfddsvfdvvdfvfdvf');