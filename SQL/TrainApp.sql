drop database if exists TrainApp;
create database if not exists TrainApp;

use TrainApp;

create table Customer (
	userName varchar(50) primary key, 
	password varchar(50), 
	email varchar(100),
	firstName varchar(30), 
	lastName varchar(30),
	isChild boolean, 
	isSenior boolean,
	isDisabled boolean, 
	discount float
);

create table Employee(
	SSN varchar(11),
    userName varchar(50) primary key,
    password varchar(50),
    firstName varchar(30),
    lastName varchar(30)
);

create table Admin(
	password varchar(50)
);
insert into Admin values ('password');

create table SupportTicket(
	supportTicketID int primary key AUTO_INCREMENT,
    userName varchar(50),
    title varchar(50),
    body varchar(5000),
    foreign key (userName) references Customer (userName)
);

create table SupportTicketMessage(
	messageID int primary key AUTO_INCREMENT,
    supportTicketID int,
    userNameCustomer varchar(50),
    userNameEmployee varchar(50),
    body varchar(5000),
    foreign key (supportTicketID) references SupportTicket (supportTicketID) on delete cascade,
    foreign key (userNameCustomer) references Customer (userName) on delete cascade,
    foreign key (userNameEmployee) references Employee (userName) on delete cascade
);