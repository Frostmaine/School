package database;

import Assignment_5.Account;
import Assignment_5.Address;
import Assignment_5.Customer;
import Assignment_5.Database;
import Assignment_5.Money;
import Assignment_5.MySQL;
import Assignment_5.Name;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import utilities.ErrorLogger;

/**
 *
 * @author frostmaine
 */
public class DatabaseMySQLImpl implements Database {

    private static Connection conn;

    public DatabaseMySQLImpl() {
        conn = new MySQL().getConnection();
    }

    @Override
    public Account insertAccount(Account account) {

        String insert = "insert into account (accountNumber, accountOwner, accountBalance)"
                + "values (?,?,?);";
        try (PreparedStatement insertStatement = conn.prepareStatement(insert);) {
            if (accountRecordExists(account.getAccountNumber())) // determine if a record exists
                return null;

            // Add the account if the above is false
            insertStatement.setInt(1, account.getAccountNumber());
            insertStatement.setInt(2, account.getOwner());
            insertStatement.setDouble(3, account.getAccountBalance().getAmount().doubleValue());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            ErrorLogger.log(Level.SEVERE, "Invalid sql statement: method insertAccount");
        }
        return account;
    }

    @Override
    public Account debit(int accountId, Money amount) {
        String recordString = "select * from account where accountNumber = ?;";
        String updateString = "update account set accountBalance = ? where accountNumber = ?;";
        Account account = null;
        Money balance = null;
        try (PreparedStatement recordStatement = conn.prepareStatement(recordString);
             PreparedStatement updateStatement = conn.prepareStatement(updateString)) {
            recordStatement.setInt(1, accountId);
            ResultSet record = recordStatement.executeQuery();
            if (record.next()) // get the record
            {
                balance = new Money(record.getDouble("accountBalance"));
                if (balance.compareTo(amount) < 0) // trying to withdraw too much
                {
                    return null;
                } else { // the deduction is valid
                    balance.spend(amount);
                    account = new Account(record.getInt("accountNumber"),
                            record.getInt("accountOwner"),
                            balance);
                    // update the record
                    updateStatement.setDouble(1, balance.getAmount().doubleValue());
                    updateStatement.setInt(2, accountId);
                    updateStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            ErrorLogger.log(Level.SEVERE, "Invalid sql statement: method debit");
        }
        return account;
    }

    @Override
    public Account credit(int accountId, Money amount) {
        String recordString = "select * from account where accountNumber = ?;";
        String updateString = "update account set accountBalance = ? where accountNumber = ?;";
        Account account = null;
        Money balance = null;
        try (PreparedStatement recordStatement = conn.prepareStatement(recordString);
             PreparedStatement updateStatement = conn.prepareStatement(updateString)) {
            recordStatement.setInt(1, accountId);
            ResultSet record = recordStatement.executeQuery();
            if (record.next()) // get the record
            {
                balance = new Money(record.getDouble("accountBalance"));
                if (balance.compareTo(amount) < 0) // trying to withdraw too much
                {
                    return null;
                } else { // the addition 
                    balance.earn(amount);
                    account = new Account(record.getInt("accountNumber"),
                            record.getInt("accountOwner"),
                            balance);
                    // update the record
                    updateStatement.setDouble(1, balance.getAmount().doubleValue());
                    updateStatement.setInt(2, accountId);
                    updateStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            ErrorLogger.log(Level.SEVERE, "Invalid sql statement: method credit");
        }
        return account;
    }

    @Override
    public Account transfer(int fromAccountId, int toAccountID, Money amount) {
        String recordString = "select accountBalance from account where accountNumber = ?;";
        String updateString = "update account set accountBalance = ? where accountNumber = ?;";
        Money fromAccount = null;
        Money toAccount = null;
        try (PreparedStatement recordStatement = conn.prepareStatement(recordString);
             PreparedStatement updateStatement = conn.prepareStatement(updateString))
        {
            // Get the fromAccount
            recordStatement.setInt(1, fromAccountId);
            ResultSet account = recordStatement.executeQuery();
            if (account.next()) // process the records
                fromAccount = new Money(account.getDouble("accountBalance"));
            else return null;
            
            // Get the to account
            recordStatement.setInt(1, toAccountID);
            account = recordStatement.executeQuery();
            if (account.next())
                toAccount = new Money(account.getDouble("accountBalance"));
            else return null;
            
            if (fromAccount.compareTo(amount) < 0) // the transfer amount is too large
                return null;
            
            // transfer the money
            fromAccount.spend(amount);
            toAccount.earn(amount);
            
            // update the records
            updateStatement.setDouble(1, fromAccount.getAmount().doubleValue());
            updateStatement.setInt(2, fromAccountId);
            updateStatement.executeUpdate();
            
            updateStatement.setDouble(1, toAccount.getAmount().doubleValue());
            updateStatement.setInt(2, toAccountID);
            updateStatement.executeUpdate();
            
            return new Account(account.getInt("accountNumber"),
                               account.getInt("accountOwner"),
                               toAccount); // return the destination account
            
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "Invalid sql statement: method transfer");
        }
        return null;
    }

    @Override
    public boolean deleteAccount(int accountNumber) {
        
        String deleteString = "if delete from account where accountNumber = ?";
        
        try (PreparedStatement deleteStatement = conn.prepareStatement(deleteString))
        {
            if (accountRecordExists(accountNumber))
            {
                deleteStatement.setInt(1, accountNumber);
                deleteStatement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "Invalid SQL statement: method deleteAccount");
        }
        
        return false;
    }

    @Override
    public Collection<Account> getAllAccounts() {
        String recordString = "select * from account;";
        Account temp = null;
        Collection<Account> out = new LinkedList<>();
        try (PreparedStatement recordStatement = conn.prepareStatement(recordString))
        {
            ResultSet records = recordStatement.executeQuery();
            while(records.next())
            {
                temp = new Account(records.getInt("accountNumber"),
                                   records.getInt("accountOwner"),
                                   new Money(records.getDouble("accountBalance")));
                out.add(temp);
            }
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "Invalid SQL statement: method getAllAccounts");
        }
        return out;
    }

    @Override
    public Customer insertCustomer(Customer customer) {
        String insertString = "insert into customer (customerSince, customerName, addressID, birthdate)"
                + "values (?,?,?,?);";
        if (customer.getCustomerIdentificationKey()<0) // add to the database
        {
            try (PreparedStatement insertStatement = conn.prepareStatement(insertString))
            {
                insertStatement.setDate(1, Date.valueOf(customer.getCustomerSince()));
                insertStatement.setString(2, customer.getName().toString());
                insertStatement.setInt(3, getAddressID(customer.getAddress()));
                insertStatement.setDate(4, Date.valueOf(customer.getBirthdate()));
                
                insertStatement.executeUpdate();
                return customer;
                
            } catch (SQLException ex) {
                ErrorLogger.log(Level.SEVERE, "Invalid SQl statement: method insertCustomer");
            }
            return customer;
        }
            
        if (customerRecordExists(customer.getCustomerIdentificationKey()))// if exists update record
        {
            updateCustomer(customer);
            return customer;
        }
        
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        String updateString = "update customer set customerSince=?, customerName=?, addressID=?, birthdate=?;";
        
        try (PreparedStatement updateStatement = conn.prepareStatement(updateString))
        {
            if (customerRecordExists(customer.getCustomerIdentificationKey()))
            {
                updateStatement.setDate(1, Date.valueOf(customer.getCustomerSince()));
                updateStatement.setString(2, customer.getName().toString());
                updateStatement.setInt(3, getAddressID(customer.getAddress()));
                updateStatement.setDate(1, Date.valueOf(customer.getBirthdate()));
                updateStatement.executeQuery();
                return null;
            }
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "Invalid SQL statement: method updateCustomer");
        }
        return customer;
    }

    @Override
    public boolean deleteCustomer(int customerIdentificationNumber) {
        String deleteString = "delete from customer where customerIdentificationKey=?;";
        
        try (PreparedStatement deleteStatement = conn.prepareStatement(deleteString))
        {
            deleteStatement.setInt(1, customerIdentificationNumber);
            if (customerRecordExists(customerIdentificationNumber)) // check for existence
            {
                deleteStatement.executeUpdate();
                return true;
            }
            
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "Invalid SQl statement: method deleteCustomer");
        }
        return false;
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        String recordString = "select * from customer;";
        Customer temp = null;
        Collection<Customer> out = new LinkedList<>();
        try (PreparedStatement recordStatement = conn.prepareStatement(recordString))
        {
            ResultSet records = recordStatement.executeQuery();
            while(records.next())
            {
                String[] name = records.getString("customer Name").split(" ");
                temp = new Customer(records.getInt("customerIdentificationKey"),
                                    records.getDate("customerSince").toLocalDate(),
                                    new Name(name[0], name[1]),
                                    queryAddress(records.getInt("addressID")),
                                    records.getDate("birthdate").toLocalDate());
                out.add(temp);
            }
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "Invalid SQL statement: method getAllAccounts");
        }
        return out;
    }
    
    /**
     * Determines if the given accountNumber is exists in the account table
     * @param accountNumber the accountNumber being searched
     * @return true- the account number exists, false - the account is missing
     */
    private boolean accountRecordExists(int accountNumber)
    {
        String test = "select count(1) from account where accountNumber = ?;";
        
        boolean exists = false;
        try (PreparedStatement testStatement = conn.prepareStatement(test))
        {
            testStatement.setInt(1, accountNumber);
            ResultSet r = testStatement.executeQuery();
            exists = r.first();
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "Invalid SQL statement: method accountRecordExists");
        }
        return exists;
    }
    
    /**
     * Determines if a given customerID exists within the customer table
     * @param customerId the id being searched
     * @return true, the record exists in the database, false - the record is missing
     */
    private boolean customerRecordExists(int customerId)
    {
        
        String test = "select count(1)from customer where customerIdentificationKey = ?;";
        boolean exists = false;
        try (PreparedStatement testStatement = conn.prepareStatement(test))
        {
            testStatement.setInt(1, customerId);
            ResultSet r = testStatement.executeQuery();
            exists = r.first();
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "Invalid SQL statement: method accountRecordExists");
        }
        return exists;
    }
    
    /**
     * Returns the id the address is located at, inserts the address if it does not exist
     * @param address
     * @return 
     */
    private int getAddressID(Address address)
    {
        int addressID = 0;
        String selectString = "select customerID from address where addressLine1=? and addressLine2=?"
                + "and addressCity=? and addressState=? and addressZipCode=?;";
        String insertString = "insert into address (addressLine1, addressLine2, addressCity, addressState, addressZipCode)"
                + "values (?,?,?,?,?);";
        try (PreparedStatement selectStatement = conn.prepareStatement(selectString);
             PreparedStatement insertStatement = conn.prepareStatement(insertString))
        { // find the address
            selectStatement.setString(1, address.getAddressLine1());
            selectStatement.setString(2, address.getAddressLine2());
            selectStatement.setString(3, address.getCity());
            selectStatement.setString(4, address.getState());
            selectStatement.setString(5, address.getZipCode());
            ResultSet record = selectStatement.executeQuery();
            if (record.next()) // it is in the database
                addressID = record.getInt("customerID");
            else // needs and entry in the database, insert it
            {
                insertStatement.setString(1, address.getAddressLine1());
                insertStatement.setString(1, address.getAddressLine2());
                insertStatement.setString(1, address.getCity());
                insertStatement.setString(1, address.getState());
                insertStatement.setString(1, address.getZipCode());
                insertStatement.executeUpdate();
            }
            record = selectStatement.executeQuery();
            if (record.next()) // get the key of the new entry
                addressID = record.getInt("customerID");
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "Invalid SQL statement: method getAddressID");
        }
        return addressID;
    }
    
    /**
     * Returns an address object keyed by the customerID field
     * @param addressID the key of the address record, null if address is not in database
     * @return an address object
     */
    private Address queryAddress(int addressID)
    {
        String selectString = "select * from address where customerID = ?;";
        
        try (PreparedStatement selectStatement = conn.prepareStatement(selectString))
        {
            selectStatement.setInt(1, addressID);
            ResultSet addr = selectStatement.executeQuery();
            if (addr.next())
                return new Address(addr.getString("addressLine1"),
                                   addr.getString("addressLine2"),
                                   addr.getString("addressCity"),
                                   addr.getString("addressState"),
                                   addr.getString("addressZipCode"));
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "invalid SQL statement: method queryAddress");
        }
        return null;
    }
    
    /**
     * Retrieve the static Connection object
     * @return the database connection
     */
    public Connection getConnection()
    {
        return conn;
    }
    
}
