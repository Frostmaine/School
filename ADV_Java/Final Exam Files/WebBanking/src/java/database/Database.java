package database;

import java.sql.*;
import java.util.Collection;
import common.*;   

/**
 * @author cjones
 */
public interface Database {
    // All implementation code needs to check for and handle errors/exceptions.

    /**
     * This method inserts a new account into the database.
     * It returns the complete account inserted or null if unsuccessful.
     * The account number should be set to a number less than zero
     * when calling this method. The method will verify the customer
     * identification number exists in the database before inserting the account.
     *
     * @param account The account to be inserted into the database.
     * @return The account as inserted or null if the account is not inserted.
     */
    Account newAccount(Account account) ;

    Account updateAccount(Account account);
    
    /**
     * This method returns the account information in the database
     * after the debit operation has finished or null if the accountID
     * is invalid.The amount will be deducted from this account as long as
     * the resulting balance is greater than or equal to zero.
     *
     * @param accountId The number of the account to be debited.
     * @param amount    The amount being debited.
     * @return The resulting account after this operation has finished
     * or null if no database changes are made.
     */
    Account debit(int accountId, Money amount) ;

    /**
     * This method returns the account information in the database
     * after the credit operation has finished or null if the accountID
     * is invalid.The amount will be append to this account.
     *
     * @param accountId The number of the account to be debited.
     * @param amount    The amount being appended.
     * @return The resulting account after this operation has finished
     * or null if no database changes are made.
     */
    Account credit(int accountId, Money amount) ;

    /**
     * This method returns the account information in the database
     * after the debit operation has finished or null if the accountID of
     * either account is invalid.The amount will be deducted fromAccountId as long as
     * the resulting balance is greater than or equal to zero. The toAccountID will then
     * be appended the amount taken from fromAccountID.
     *
     * @param fromAccountId The number of the account to be debited.
     * @param toAccountID   The number of the account to be appended.
     * @param amount        The amount being debited.
     * @return The resulting accounts after this operation has finished
     * or null if no database changes are made.
     */
    Account transfer(int fromAccountId, int toAccountID, Money amount) ;

    /**
     * checks to see if the account number is valid and deletes it. Return true if account was
     * found and deleted. Returns false if account wasn't found
     *
     * @param accountNumber The number of the account to delete.
     * @return True if account was deleted
     */
    boolean deleteAccount(int accountNumber) ;

    /**
     * Retrieves all the account from the account table and returns them in a list
     *
     * @return a list of all the accounts
     */
    Collection<Account> getAllAccounts() ;

    /**
     * This method returns the complete Customer object as updated or null. It will
     * insert the customer as a new row in the customer table if the
     * customer identification number is less than zero.
     * if the customer identification number is >=0 and does not already exists, then nothing happens
     * and null is returned.
     *
     * @param customer The customer to be updated or inserted in the database.
     * @return The customer as updated or null if no database changes are made.
     */
    Customer insertCustomer(Customer customer) ;

    /**
     * This method returns the info of a customer that was updated. It will find the customer with the matching
     * customerID as the customer inputted and set all the values to the ones given in the parameter customer.
     * returns null if there is no match of the customerID or if ID < 0
     *
     * @param customer The customer to be updated in the database.
     * @return The customer as updated or null if no database changes are made.
     */
    Customer updateCustomer(Customer customer) ;

    /**
     * find a match to the parameter customerIdentificationNumber, If no match returns false and
     * no change to the table. If match is found, returns true and the customer is deleted from the
     * Table
     *
     * @param customerIdentificationNumber ID of the customer to remove
     * @return True if customer was deleted, false if no customer was found.
     */
    boolean deleteCustomer(int customerIdentificationNumber) ;

    /**
     * Creates a Arraylist of customers and goes through each row in customer table and
     * gets the values of the customer and adds the customer to the array
     *
     * @return A list of customers.
     */
    Collection<Customer> getAllCustomers() ;

    /**
     * Check to see if the customer is found in the customer table base on their Customer
     * ID. Returns false if no account was found
     *
     * @param id The number of the account to delete.
     * @return True if a customer match is found
     */
    boolean customerExists(int id);

    /**
     * Check to see if the account is found in the customer table base on their Customer
     * ID. Returns false if no account was found
     *
     * @param id The number of the account to delete.
     * @return True if a account id has a match
     */
    boolean checkAccount(int id);

    /**
     * checks to see if a given address is inside in the table and returns the address id.
     * If no match is found, then the address is added to the table and returns the new
     * address id
     *
     * @param address The number of the account to delete.
     * @return True if a account id has a match
     */
    int insertAddress(Address address);

    Address getAddress(int addressID);

    public boolean validateDebit(double balance, int accountNum);

    public String accountReport(int accountNum);

    public String reportAllAccounts();


}