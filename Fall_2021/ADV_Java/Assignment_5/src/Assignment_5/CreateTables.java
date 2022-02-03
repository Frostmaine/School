package Assignment_5;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates the tables for the database
 *
 * @author frostmaine
 */
public class CreateTables {

    public static void main(String[] args) throws SQLException {

        // Connect to the data base and prepare a statment object
        MySQL mySQL = new MySQL();
        Connection conn = mySQL.getConnection();
        Statement statement = conn.createStatement();

        statement.execute("drop table account;");
        statement.execute("drop table customer;");
        statement.execute("drop table address;");

        statement.execute("create table if not exists address (\n"
                + "    customerID int not null auto_increment=1,\n"
                + "    addressLine1 varchar(255) not null,\n"
                + "    addressLine2 varchar(255),\n"
                + "    addressCity varchar(255) not null,\n"
                + "    addressState varchar(255) not null,\n"
                + "    addressZipCode varchar(255) not null,\n"
                + "    PRIMARY KEY (customerID)\n"
                + ");\n"
                + "");
        statement.execute("create table if not exists customer (\n"
                + "    customerIdentificationKey int not null auto_increment=1,\n"
                + "    customerSince DATE not null,\n"
                + "    customerName varchar(255) not null,\n"
                + "    addressID int not null,\n"
                + "    birthdate DATE not null,\n"
                + "    PRIMARY KEY (customerIdentificationKey),\n"
                + "    FOREIGN KEY (addressID) REFERENCES  address (customerID)\n"
                + ");\n"
                + "");
        statement.execute("create table if not exists account (\n"
                + "    accountNumber int not null,\n"
                + "    accountOwner int not null, /* customer can have multple accounts */\n"
                + "    accountBalance decimal(65, 2) not null,\n"
                + "    PRIMARY KEY (accountNumber),\n"
                + "    FOREIGN KEY (accountOwner) REFERENCES customer (customerIdentificationKey)\n"
                + ");");

        // fill the tables with entries
        statement.execute("insert into customer (customerSince, customerName, addressID, birthdate)\n"
                + "values\n"
                + "	('2015-03-16', 'Jack Nicholson', 2, '1937-04-22'),\n"
                + "    ('2014-03-16', 'Steven King', 2, '1947-09-21'),\n"
                + "    ('2001-03-31', 'Matthew Yackiel', 1, '1997-03-31'),\n"
                + "    ('2001-04-01', 'Nancy Pelosi', 3, '1940-03-26'),\n"
                + "    ('2004-08-29', 'Morgan Lewis', 5, '1999-9-08'),\n"
                + "    ('2006-4-12', 'Hayden Lewis', 4, '2001-1-8');");

        statement.execute("insert into account (accountOwner, accountBalance)\n"
                + "values\n"
                + "    (3, 2784.99),\n"
                + "    (1, 4000),\n"
                + "    (2, 85934.55),\n"
                + "    (4, 18495.64),\n"
                + "    (6, 1010.10);");
        statement.execute("insert into address (addressLine1, addressLine2, addressCity, addressState, addressZipCode)\n"
                + "values \n"
                + "	('557 Clints Ln', '', 'Bloomsburg', 'Pa', '17815'),\n"
                + "    ('4117 13th St', '', 'Menominee', 'Mi', '49858'),\n"
                + "    ('3111 Pepperridge Dr', '', 'Antelope', 'Ca', '95843'),\n"
                + "    ('13 Cub Circ', '', 'Bloomsburg', 'Pa' ,'17815');");
        
        mySQL.closeConnection(conn);
    }

}
