drop table if exists Compsci221.Accounts;
drop table if exists Compsci221.Customers;
drop table if exists Compsci221.Addresses;

create table if not exists Compsci221.Addresses (

    Address_ID				int auto_increment,
    Address_Street			varchar(255),
    Address_City			varchar(255),
    Address_State			varchar(2),
    Address_Zip                         int,
    primary key (Address_ID)
);

create table if not exists Compsci221.Customers (

    Customer_Identification	int auto_increment,
    Customer_Firstname		varchar(255),
    Customer_Lastname		varchar(255),
    Customer_Birthday		date,
    Customer_Start_Date		date,
    Customer_Address_ID		int,

     primary key (Customer_Identification),
    foreign key (Customer_Address_ID) references Compsci221.Addresses(Address_ID)
);

create table if not exists Compsci221.Accounts (

	Account_Number					int auto_increment,
	Account_Balance					decimal(13,4), 
        Account_Owner					int not null,
	primary key (Account_Number),
      foreign key (Account_Owner) references Compsci221.Customers(Customer_Identification)
);

insert into Compsci221.Addresses
    (Address_Street, Address_City, Address_State, Address_Zip) values
        ("84 Service Lane",  "Bloomsburg", "PA", "17815"),
	("PO Box 3112", "Bloomsburg", "PA", "17815"),
        ("400 East Second St.",  "Bloomsburg", "PA", "17815"),
        ("214 Narehood Rd.",  "Danville", "PA", "17821"),
        ("2 Scotch Pine",  "Hazleton", "PA", "18202");

insert into Compsci221.Customers
    (Customer_Firstname, Customer_Lastname, Customer_Birthday,  Customer_Start_Date, Customer_Address_ID) values
        ("Jane", "Smith", "2002/9/30", "2019/10/7", "03"),
		("Niles", "Rosenthal", "2000/4/6", "2021/10/29", "02"),
		("Amber", "Kenney", "1974/1/2", "1995/7/19", "01"),
		("Dottie", "Edwards", "1941/5/23", "1960/5/4", "04"),
		("Michael", "Kenney", "1972/1/2", "1991/9/21", "05");

insert into Compsci221.Accounts (Account_Balance, Account_Owner) values
        ("11.0000", "01"),
        ("63.4700", "02"),
	("879.9567", "03"),
	("10271.2301", "04"),
	("367.5921", "05");