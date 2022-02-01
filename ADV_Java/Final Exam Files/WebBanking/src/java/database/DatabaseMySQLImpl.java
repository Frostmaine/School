package database;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import common.*; 
import java.util.logging.Level;
import java.time.LocalDate; // import the LocalDate class
import java.util.LinkedList;
import java.util.List;


public class DatabaseMySQLImpl implements Database {

    @Override
    public Account newAccount(Account account)  {
      Connection connection = mysql.Web_MYSQL_Helper.getConnection();
      try {
            if (account.getAccountNumber() > 0) {
                utilities.WebErrorLogger.log(Level.SEVERE,
                 "New Account Error: Incorrect Account ID of " +account.getAccountNumber() );
                return null;
            }
            if (!customerExists(account.getOwner())) {
                utilities.WebErrorLogger.log(Level.SEVERE,
                 "New Account Error: Incorrect Customer ID of "+ account.getOwner() );
                return null;
            }

           
            //puts the last added account at the top
            String sql = "INSERT INTO Accounts(Account_Owner, Account_Balance)" +
                    "VALUES ("+account.getOwner()+","+account.getAccountBalance().getAmount()+")";

            Statement s1 = connection.createStatement() ;
            
            s1.executeUpdate( sql , Statement.RETURN_GENERATED_KEYS ); 
            ResultSet rs1 = s1.getGeneratedKeys();
            int accountNum = -1;
            if(rs1.next()){
                accountNum = rs1.getInt(1);
            }
            mysql.Web_MYSQL_Helper.closeConnection(connection);
     
            return new Account(accountNum, account.getOwner(), account.getAccountBalance());
        } catch (SQLException ex) {
            utilities.WebErrorLogger.log(Level.SEVERE, "Insert Account Error"+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
      }
        return null;
    }

    @Override
    public Account debit(int accountId, Money amount)  {
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try {
            //checks for valid account
            if (!checkAccount(accountId)) {
                utilities.WebErrorLogger.log(Level.SEVERE,
                        "debit error: Incorrect Account ID of " +accountId );
                return null;
            }
            
            String sql = "Select * From Accounts Where Account_Number = ?";
            PreparedStatement p1 = connection.prepareStatement(sql);
            
            p1.setInt(1, accountId);
            ResultSet rs = p1.executeQuery();
            BigDecimal total = new BigDecimal(0);
            int owner = -1;
            if (rs.next()) {
                owner = rs.getInt("Account_Owner");
                BigDecimal balance = (rs.getBigDecimal("Account_Balance"));
                total = balance.subtract(amount.getAmount());
                if (total.compareTo(BigDecimal.ZERO) < 0) {
                    utilities.WebErrorLogger.log(Level.SEVERE,
                            "Not enough funds in account to withdrawal ");
                    return null;
                }
            }
            sql = "UPDATE Accounts    SET Account_Balance = ?   WHERE Account_Number = ?;";
            PreparedStatement p2 = connection.prepareStatement(sql);
            p2.setBigDecimal(1, total);
            p2.setInt(2, accountId);
            p2.executeUpdate();
            
            mysql.Web_MYSQL_Helper.closeConnection(connection);
            return new Account(accountId, owner, new Money(total));
        } catch (SQLException ex) {
             utilities.WebErrorLogger.log(Level.SEVERE, "Debit Account Error"+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Account credit(int accountId, Money amount)  {
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try {
            if (!checkAccount(accountId)) {
                utilities.WebErrorLogger.log(Level.SEVERE,
                        "credit error: Incorrect Account ID of " +accountId );
                return null;
            }

            //Gets account that is equal to inputted account id
            String sql = "Select * From Accounts Where Account_Number = ?";
            PreparedStatement p1 = connection.prepareStatement(sql);
            p1.setInt(1, accountId);
            ResultSet rs = p1.executeQuery();
            
            //sets all values to -1 so if not found then returns null
            BigDecimal total = BigDecimal.valueOf(-1);
            int owner = -1;
            while (rs.next()) {
                owner = rs.getInt("Account_Owner");
                BigDecimal balance = (rs.getBigDecimal("Account_Balance"));
                total = balance.add(amount.getAmount());
            }
            //updates the balance to new balance if funds are still in the account
            sql = "UPDATE Accounts    SET Account_Balance = ?   WHERE Account_Number = ?;";
            PreparedStatement p2 = connection.prepareStatement(sql);
            p2.setBigDecimal(1, total);
            p2.setInt(2, accountId);
            p2.executeUpdate();
            
            connection.close();
            //returns the account with new balance
            return new Account(accountId, owner, new Money(total));
        } catch (SQLException ex) {
             utilities.WebErrorLogger.log(Level.SEVERE, "Credit Account Error"+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Account transfer(int fromAccountId, int toAccountID, Money amount) {

        //check that accounts are valid
        if (!checkAccount(fromAccountId)) {
            utilities.WebErrorLogger.log(Level.SEVERE,
                        "Account ID withdrawing from was not found " +fromAccountId );
            return null;
        }
        if (!checkAccount(toAccountID)) {
            utilities.WebErrorLogger.log(Level.SEVERE,
                        "Account ID to receive funds from was not found " +toAccountID );
            return null;
        }

        //if funds were successfully taken out of fromAccount, then adds funds to toAccount
        if (debit(fromAccountId, amount) != null) return credit(toAccountID, amount);
        //else returns null
        return null;
    }

    @Override
    public boolean deleteAccount(int accountNumber) {
        //get connection
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try {
            if (!checkAccount(accountNumber)) {
                utilities.WebErrorLogger.log(Level.SEVERE,
                        "A deleteAccount error-- Account ID for delete from was not found" +accountNumber );
                return false;
            }
            
            
            //Gets account that is equal to inputted account id
            String sql = "DELETE From Accounts Where Account_Number = ?";
            PreparedStatement p1 = connection.prepareStatement(sql);
            p1.setInt(1, accountNumber);
            p1.executeUpdate();
            return true;
        } // returns true if successful, false otherwise
        catch (SQLException ex) {
            utilities.WebErrorLogger.log(Level.SEVERE, "Delete Account Error "+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Collection<Account> getAllAccounts() {
        //get connection
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        //create ArrayList
        ArrayList<Account> accounts = new ArrayList<>();     
        try {
            //Gets account that is equal to inputted account id
            String sql = "Select * From Accounts Order By Account_Number ASC";
            PreparedStatement p1 = connection.prepareStatement(sql);
            ResultSet rs = p1.executeQuery();
            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt("Account_Number"),
                        rs.getInt("Account_Owner"),
                        new Money(rs.getBigDecimal("Account_Balance"))));
            }
            connection.close();
            return accounts;
        } //returns all accounts from the database in an Arraylist
        catch (SQLException ex) {
            utilities.WebErrorLogger.log(Level.SEVERE, "Get All Accounts Error "+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return accounts;

    }

    @Override
    public Customer insertCustomer(Customer customer) {
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try {    
            if (customer.getCustomerIdentificationKey() > 0) {
                utilities.WebErrorLogger.log(Level.SEVERE,
                 "Insert Customer Error: Incorrect Cuatomer ID of " +customer.getCustomerIdentificationKey() );
                return null;
            }
            int addressID = insertAddress(customer.getAddress());
            customer.getAddress().setAddressID(addressID);
            Statement statement = connection.createStatement();
            //Get a statement from the connection
            String sql = "INSERT INTO Customers(Customer_Start_Date,Customer_Firstname,Customer_Lastname," +
                    "Customer_Birthday,Customer_Address_ID)" +
                    "VALUES (?,?,?,?,?)";
            String select = "SELECT Customer_Identification FROM Customers ORDER BY Customer_Identification DESC";
            PreparedStatement p1 = connection.prepareStatement(sql);
            
            p1.setDate(1, Date.valueOf(customer.getCustomerSince()));
            p1.setString(2, customer.getName().getFirstName());
            p1.setString(3, customer.getName().getLastName());
            p1.setDate(4, Date.valueOf(customer.getBirthdate()));
            p1.setInt(5, addressID);
            // p1.setInt(5, 1);
            p1.executeUpdate();
            
            //gets the largest account_Number which should be the new account
            ResultSet rs = statement.executeQuery(select);
            rs.next();
            int customerID = rs.getInt("Customer_Identification");
            connection.close();
            
            return new Customer(customerID, customer.getCustomerSince(),
                    customer.getName(), customer.getAddress(), customer.getBirthdate());
        } catch (SQLException ex) {
            utilities.WebErrorLogger.log(Level.SEVERE, "insertCustomer error "+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer)  {

        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try {          
            if (customer.getCustomerIdentificationKey() < 0) {
                
                return null;
            }
            int addressID = checkAddress(customer.getAddress().getAddressID());
            if (addressID <= 0) {
                
                return null;
            }
            if (!customerExists(customer.getCustomerIdentificationKey())) {
                
                return null;
            }
            
            String sql = "UPDATE Customers  " +
                    "  SET" +
                    " Customer_Start_Date = ?, " +
                    " Customer_FirstName = ?, " +
                    " Customer_LastName = ?, " +
                    " Customer_Birthday = ?, " +
                    " Customer_Address_ID = ? " +
                    " WHERE Customer_Identification = ?;";
            PreparedStatement p1 = connection.prepareStatement(sql);
            
            p1.setDate(1, Date.valueOf(customer.getCustomerSince()));
            p1.setString(2, customer.getName().getFirstName());
            p1.setString(3, customer.getName().getLastName());
            p1.setDate(4, Date.valueOf(customer.getBirthdate()));
            p1.setInt(5, addressID);
            p1.setInt(6, customer.getCustomerIdentificationKey());
            p1.executeUpdate();
            
            
            return customer;
        } catch (SQLException ex) {
            utilities.WebErrorLogger.log(Level.SEVERE, "updateCustomer error "+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        
        return null;
    }

    @Override
    public boolean deleteCustomer(int customerIdentificationNumber) {
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();;
        try {
            if (!customerExists(customerIdentificationNumber)) {
           
                return false;
            }
            

            
            String sql = "DELETE From Accounts Where Account_Owner = ?";
            PreparedStatement p1 = connection.prepareStatement(sql);
            p1.setInt(1, customerIdentificationNumber);
            p1.executeUpdate();
            
            //Gets account that is equal to inputted account id
            sql = "DELETE From Customers Where Customer_Identification = ?";
            p1 = connection.prepareStatement(sql);
            p1.setInt(1, customerIdentificationNumber);
            p1.executeUpdate();
            return true;
        } catch (SQLException ex) {
            utilities.WebErrorLogger.log(Level.SEVERE, "deleteCustomer error "+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        
        return false;
    }

    @Override
    public Collection<Customer> getAllCustomers() {
             //create ArrayList
            ArrayList<Customer> customers = new ArrayList<>();       
            Connection connection = mysql.Web_MYSQL_Helper.getConnection();    
        try {

            //Gets account that is equal to inputted account id
            String sql = "Select * From Customers Order By Customer_Identification ASC";
            PreparedStatement p1 = connection.prepareStatement(sql);
            ResultSet rs = p1.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("Customer_Identification"),
                        (rs.getDate("Customer_Start_Date").toLocalDate()),
                        new Name(rs.getString("Customer_FirstName"), rs.getString("Customer_LastName")),
                        getAddress(rs.getInt("Customer_Address_ID")),
                        rs.getDate("Customer_Birthday").toLocalDate()
                        
                ));
            }
            connection.close();
            return customers;
        } catch (SQLException ex) {
            utilities.WebErrorLogger.log(Level.SEVERE, "getAllCustomers error "+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return customers;
    }

    @Override
    public boolean customerExists(int id) {
        String query = "SELECT Customer_Identification FROM Customers";
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try  {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getInt("Customer_Identification") == id) return true;
            }

        } catch (SQLException ex) {
         utilities.WebErrorLogger.log(Level.SEVERE, "Customer Exists error "+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean checkAccount(int id) {
        String query = "SELECT Account_Number FROM Accounts where Account_Number ="+id;
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                if (rs.getInt("Account_Number") == id) return true;
            }
            else return false;

        } catch (SQLException ex) {
         utilities.WebErrorLogger.log(Level.SEVERE, "Check Account error "+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return false;

    }

    @Override
    public int insertAddress(Address address) {
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        String query;
        try {
            Statement stmt = connection.createStatement();
            query = "INSERT INTO Addresses(Address_Street,  Address_City, Address_State, Address_Zip)" +
                    " VALUES ('"+address.getStreetAddress()+"','"+address.getCity()+"','"+address.getState()+"','"+address.getZipCode()+"')";

            System.out.println(query);
            stmt.executeUpdate( query , Statement.RETURN_GENERATED_KEYS ); 
            ResultSet rs1 = stmt.getGeneratedKeys();
            if(rs1.next()){
                return rs1.getInt(1);
            } else return -1; 

        } catch (SQLException ex) {
          utilities.WebErrorLogger.log(Level.SEVERE, "Insert Address error "+ex.getMessage(), ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return 0;
    }

 
    public int checkAddress(int addressID) {
        String query = "SELECT * FROM Addresses where Address_ID="+addressID;
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                 mysql.Web_MYSQL_Helper.closeConnection(connection);
                 return addressID;
            }

        } catch (SQLException ex) {
          utilities.WebErrorLogger.log(Level.SEVERE, "Check Address error", ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return 0;
    }

    @Override
    public Address getAddress(int addressID) {
        String query = "SELECT * FROM Addresses";
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int currentAddressID = rs.getInt("Address_ID");
                if(currentAddressID == addressID) {
                    return new Address(currentAddressID,
                            rs.getString("Address_Street"),
                            rs.getString("Address_City"),
                            rs.getString("Address_State"),
                            rs.getString("Address_Zip")
                    );
                }
            }
            
        } catch (SQLException ex) {
          utilities.WebErrorLogger.log(Level.SEVERE, "Get Address error", ex);
        } finally {
           mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return null;
    }
    
    public boolean validateDebit(double amount, int accountNum)
    {
        String query = "select * from Accounts where Account_Number=?";
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) // grab the account
            {
                double balance = rs.getDouble("Account_Balance");
                return (balance - amount > 0) && amount >= 0;
            }
        }
        catch (SQLException ex)
        {
            utilities.WebErrorLogger.log(Level.SEVERE, "Invalid Debit Error", ex);
        }
        finally
        {
            mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Account updateAccount(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String [] args){
        System.out.println("Test of DatabaseMYSQLIMPL");
        Address address = new Address("400 East Second Street","Bloomsburg","PA","17815");
        DatabaseMySQLImpl db = new DatabaseMySQLImpl(); 

        System.out.println("The new address id is "+db.insertAddress(address));
        
        Account account = new Account(-1, 3,new Money(55.75));
        System.out.println("The new account id is "+db.newAccount(account).getAccountNumber());
        Customer customer = new Customer();
        customer.setAddress(address);
        Name name = new Name("Best", "Student");
        customer.setName(name);
        customer.setCustomerIdentificationKey(-1);
        customer.setBirthdate(LocalDate.parse("1992-12-25") );
        customer.setCustomerSince(LocalDate.now());
        System.out.println("The new customer id is "+db.insertCustomer(customer).getCustomerIdentificationKey());
    }
    
    @Override
    public String accountReport(int accountNum)
    {        
        // build the report
        String report = "";
        String account_query = "select * from Accounts where Account_Number=?";
        String customer_query = "select * from Customers where Customer_Identification=?";
        Connection connection = mysql.Web_MYSQL_Helper.getConnection();
        try {
            PreparedStatement account_stmt = connection.prepareStatement(account_query);
            PreparedStatement customer_stmt = connection.prepareStatement(customer_query);

            // get the account info
            account_stmt.setInt(1, accountNum);
            ResultSet rs = account_stmt.executeQuery();
            if (!rs.next())
                return "<h2>Invalid Account Information</h2>";
            int account = rs.getInt("Account_Number");
            Money balance = new Money(rs.getDouble("Account_Balance"));
            
            // get info about the customer
            customer_stmt.setInt(1, rs.getInt("Account_Owner"));
            rs = customer_stmt.executeQuery();
            rs.next();
            Name name = new Name(rs.getString("Customer_Firstname"), rs.getString("Customer_Lastname"));
            
            report += "<h2>" + name + "</h2><br>"
                    + "<h2>Amount in Account #" + account + " is "
                    + balance + "</h2><br>";
            return report;
        } catch (SQLException ex) {
            utilities.WebErrorLogger.log(Level.SEVERE, "Invalid Debit Error", ex);
        } finally
        {
            mysql.Web_MYSQL_Helper.closeConnection(connection);
        }
        
        return report;
    }

    @Override
    public String reportAllAccounts() {
        // set the header of the table
        String report = "<tr><th>Customer ID</th><th>Account Number</th><th>Customer Name</th><th>Balance</th></tr>";
        // get the data
        List<Customer> customers =  new LinkedList<>(getAllCustomers());
        List<Account> accounts = new LinkedList<>(getAllAccounts());
        
        int customer_id;
        Customer customer;
        for(Account acc: accounts) // iterate the accounts
        {
            customer_id = acc.getOwner();
            customer = extractCustomer(customer_id, customers); // grab the current customer
            // add a row to the table
            report += "<tr><td>" + customer_id + "</td><td>" + acc.getAccountNumber() +
                    "</td><td>" + customer.getName() + "</td><td>" 
                    + acc.getAccountBalance() + "</td></tr>";
        }
        return report; // close the table
        
    }
    
    private Customer extractCustomer(int id, List<Customer> list)
    {
        for (Customer cust: list)
            if (id == cust.getCustomerIdentificationKey())
                return cust;
        return null;
    }
}

