/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  frostmaine
 * Created: Nov 3, 2021
 */

create table if not exists address (
    customerID int not null auto_increment=1,
    addressLine1 varchar(255) not null,
    addressLine2 varchar(255),
    addressCity varchar(255) not null,
    addressState varchar(255) not null,
    addressZipCode varchar(255) not null,
    PRIMARY KEY (customerID)
);

create table if not exists customer (
    customerIdentificationKey int not null auto_increment=1,
    customerSince DATE not null,
    customerName varchar(255) not null,
    addressID int not null,
    birthdate DATE not null,
    PRIMARY KEY (customerIdentificationKey),
    FOREIGN KEY (addressID) REFERENCES  address (customerID)
);

create table if not exists account (
    accountNumber int not null,
    accountOwner int, /* customer can have multple accounts */
    accountBalance decimal(65, 2) not null,
    PRIMARY KEY (accountNumber),
    FOREIGN KEY (accountOwner) REFERENCES customer (customerIdentificationKey)
);

insert into address (addressLine1, addressLine2, addressCity, addressState, addressZipCode)
values 
	('557 Clints Ln', '', 'Bloomsburg', 'Pa', '17815'),
    ('4117 13th St', '', 'Menominee', 'Mi', '49858'),
    ('3111 Pepperridge Dr', '', 'Antelope', 'Ca', '95843'),
    ('13 Cub Circ', '', 'Bloomsburg', 'Pa' ,'17815');
    
insert into customer (customerSince, customerName, addressID, birthdate)
values
	('2015-03-16', 'Jack Nicholson', 2, '1937-04-22'),
    ('2014-03-16', 'Steven King', 2, '1947-09-21'),
    ('2001-03-31', 'Matthew Yackiel', 1, '1997-03-31'),
    ('2001-04-01', 'Nancy Pelosi', 3, '1940-03-26'),
    ('2004-08-29', 'Morgan Lewis', 5, '1999-9-08'),
    ('2006-4-12', 'Hayden Lewis', 4, '2001-1-8');
    
insert into account (accountOwner, accountBalance)
values
    (3, 2784.99),
    (1, 4000),
    (2, 85934.55),
    (4, 18495.64),
    (6, 1010.10);