/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  frostmaine
 * Created: Dec 2, 2021
 */

drop table Accounts;
drop table Customers;
drop table Addresses;

create table if not exists Addresses (
Address_ID int not null auto_increment,
Address_Street varchar(255) not null,
Address_City varchar(255) not null,
Address_State varchar(255) not null,
Address_Zip varchar(255) not null,
PRIMARY KEY (Address_ID));

create table if not exists Customers (
Customer_Identification int not null auto_increment,
Customer_Start_Date DATE not null,
Customer_Firstname varchar(255) not null,
Customer_Lastname varchar(255) not null,
Customer_Address_ID int not null,
Customer_Birthday DATE not null,
PRIMARY KEY (Customer_Identification),
FOREIGN KEY (Customer_Address_ID) REFERENCES  Addresses (Address_ID));

create table if not exists Accounts (
Account_Number int not null auto_increment,
Account_Owner int not null,
Account_Balance decimal(65, 2) not null,
PRIMARY KEY (Account_Number),
 FOREIGN KEY (Account_Owner) REFERENCES Customers (Customer_Identification));

insert into Addresses (Address_Street, Address_City, Address_State, Address_Zip)
values
('557 Clints Ln', 'Bloomsburg', 'Pa', '17815'),
('4117 13th St', 'Menominee', 'Mi', '49858'),
('3111 Pepperridge Dr', 'Antelope', 'Ca', '95843'),
('13 Cub Circ', 'Bloomsburg', 'Pa' ,'17815');

insert into Customers (Customer_Start_Date, Customer_Firstname, Customer_Lastname, Customer_Address_ID, Customer_Birthday)
values
('2015-03-16', 'Jack', 'Nicholson', 2, '1937-04-22'),
('2014-03-16', 'Steven', 'King', 2, '1947-09-21'),
('2001-03-31', 'Matthew', 'Yackiel', 1, '1997-03-31'),
('2001-04-01', 'Nancy', 'Pelosi', 3, '1940-03-26'),
('2004-08-29', 'Morgan', 'Lewis', 1, '1999-09-08'),
('2006-04-12', 'Hayden', 'Lewis', 4, '2001-01-08');

insert into Accounts (Account_Owner, Account_Balance)
values
(3, 2784.99),
(1, 4000),
(2, 85934.55),
(4, 18495.64),
(6, 1010.10);

