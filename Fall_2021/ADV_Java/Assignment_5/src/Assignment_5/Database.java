package Assignment_5;

import java.util.Collection;

/**
 *
 * @author cjones
 */
public interface Database {
    // All implementation code needs to check for and handle errors/exceptions. 
    
   /** 
    *  This method inserts a new account into the database. 
    *  It returns the complete account inserted or null if unsuccessful. 
    *  The account number should be set to a number less than zero
    *  when calling this method. The method will verify the customer 
    *  identification number exists in the database before inserting the account. 
    * 
    * @param account The account to be inserted into the database. 
    * @return The account as inserted or null if the account is not inserted. 
    */
    public Account insertAccount(Account account); 
    
    /** 
     * This method returns the account information in the database 
     * after the debit operation has finished or null if the accountID 
     * is invalid.The amount will be deducted from this account as long as 
 the resulting balance is greater than or equal to zero.  
     * 
     * @param accountId The number of the account to be debited. 
     * @param amount The amount being debited. 
     * @return The resulting account after this operation has finished 
     *         or null if no database changes are made.
     */
    public Account debit(int accountId, Money amount); 
    
    /**
     * This method returns the account information in the database after
     * the credit operation has finished or null if the accountID 
     * is invalid.
     * @param accountId the number of the account to be credited
     * @param amount the amount being credited
     * @return The resulting account after the operation has finished
     *          or null if no database changes are made.
     */
    public Account credit(int accountId, Money amount); 
    
    /**
     * This method returns the destination account information in the database
     * after the transfer operation has finished or null if either accountid
     * is invalid or there is not enough money in fromAccountId
     * @param fromAccountId the account number making the transfer
     * @param toAccountID the account number recieving the transfer
     * @param amount the amount being transferred
     * @return 
     */
    public Account transfer(int fromAccountId, int toAccountID, Money amount);
    
    /**
     * This method returns true if the account is successfully deleted, or the 
     * account does not exist and false otherwise.
     * @param accountNumber the account number being deleted
     * @return 
     */
    public boolean deleteAccount(int accountNumber);// returns true if sucessful, false otherwise
    
    /**
     * This method returns a Collection containing all the accounts currently in
     * the database
     * @return a collection of accounts
     */
    public Collection<Account> getAllAccounts();//returns all accounts from the database in an Arraylist 
    
   /** This method returns the complete Customer object as updated or null. It will 
     * insert the customer as a new row in the customer table if the 
     * customer identification number is less than zero.
     * if the customer identification number is >=0 and does not already exists, then nothing happens
     * and null is returned.
     * 
     * @param customer The customer to be updated or inserted in the database. 
     * @return The customer as updated or null if no database changes are made.
     */
    public Customer insertCustomer(Customer customer); 
    
    /**
     * This method updates the customer keyed by customerIdentificationNumber,
     * with the new customer object.  It returns null if the database is updated
     * and returns the customer object if the database remains unchanged.
     * @param customer the object being updated
     * @return 
     */
    public Customer updateCustomer(Customer customer);
    
    /**
     * Delete the customer record keyed by customerIdentificationNumber from the
     * database
     * @param customerIdentificationNumber the key to be deleted
     * @return  true if the record was deleted, false if the database is unchanged
     */
    public boolean deleteCustomer(int customerIdentificationNumber);
    
    /**
     * Place all Customers from the customer table into a collection of Customer
     * objects
     * @return a collection of customer objects
     */
    public Collection<Customer> getAllCustomers();
    
    
}
