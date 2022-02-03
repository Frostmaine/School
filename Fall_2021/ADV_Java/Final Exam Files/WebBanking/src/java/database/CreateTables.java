package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import mysql.Web_MYSQL_Helper;

/**
 * @author cjones
 */
public class CreateTables {

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        //Gets a connection to the database
        Connection connection = Web_MYSQL_Helper.getConnection();
        //Get a statement from the connection
        Statement statement = connection.createStatement();

        statement.execute("drop table Accounts;");
        statement.execute("drop table Customers;");
        statement.execute("drop table Addresses;");

        statement.execute("create table if not exists Addresses (\n"
                + "Address_ID int not null auto_increment,\n"
                + "Address_Street varchar(255) not null,\n"
                + "Address_City varchar(255) not null,\n"
                + "Address_State varchar(255) not null,\n"
                + "Address_Zip varchar(255) not null,\n"
                + "PRIMARY KEY (Address_ID));");
        
        statement.execute("create table if not exists Customers (\n"
                + "Customer_Identification int not null auto_increment,\n"
                + "Customer_Start_Date DATE not null,\n"
                + "Customer_Firstname varchar(255) not null,\n"
                + "Customer_Lastname varchar(255) not null,\n"
                + "Customer_Address_ID int not null,\n"
                + "Customer_Birthday DATE not null,\n"
                + "PRIMARY KEY (Customer_Identification),\n"
                + "FOREIGN KEY (Customer_Address_ID) REFERENCES  Addresses (Address_ID));");
        
        statement.execute("create table if not exists Accounts (\n"
                + "Account_Number int not null auto_increment,\n"
                + "Account_Owner int not null,\n"
                + "Account_Balance decimal(65, 2) not null,\n"
                + "PRIMARY KEY (Account_Number),\n"
                + " FOREIGN KEY (Account_Owner) REFERENCES Customers (Customer_Identification));");

        // fill the tables with entries
        statement.execute("insert into Addresses (Address_Street, Address_City, Address_State, Address_Zip)\n"
                + "values\n"
                + "('557 Clints Ln', 'Bloomsburg', 'Pa', '17815'),\n"
                + "('4117 13th St', 'Menominee', 'Mi', '49858'),\n"
                + "('3111 Pepperridge Dr', 'Antelope', 'Ca', '95843'),\n"
                + "('13 Cub Circ', 'Bloomsburg', 'Pa' ,'17815');");

        statement.execute("insert into Customers (Customer_Start_Date, Customer_Firstname, Customer_Lastname, Customer_Address_ID, Customer_Birthday)\n"
                + "values\n"
                + "('2015-03-16', 'Jack', 'Nicholson', 2, '1937-04-22'),\n"
                + "('2014-03-16', 'Steven', 'King', 2, '1947-09-21'),\n"
                + "('2001-03-31', 'Matthew', 'Yackiel', 1, '1997-03-31'),\n"
                + "('2001-04-01', 'Nancy', 'Pelosi', 3, '1940-03-26'),\n"
                + "('2004-08-29', 'Morgan', 'Lewis', 1, '1999-09-08'),\n"
                + "('2006-04-12', 'Hayden', 'Lewis', 4, '2001-01-08');");

        statement.execute("insert into Accounts (Account_Owner, Account_Balance)\n"
                + "values\n"
                + "(3, 2784.99),\n"
                + "(1, 4000),\n"
                + "(2, 85934.55),\n"
                + "(4, 18495.64),\n"
                + "(6, 1010.10);");

        Web_MYSQL_Helper.closeConnection(connection);
    }
}
