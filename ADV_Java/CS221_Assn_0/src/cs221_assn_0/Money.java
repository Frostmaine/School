package cs221_assn_0;

/**
 * This class represents a cash balance.  It uses a double to represent the value,
 * and supplies methods for increasing and decreasing the balance, as well as a
 * get method for the balance.
 * @author Matthew Yackiel
 */
public class Money
{
    private double balance;
    
    /**
     * the default constructor, defaults to balance of 0.0
     */
    public Money()
    {
        this(0.0);
    }

    /**
     * Initializes a Money object with a balance of balance
     * @param balance the amount of initial money on hand
     */
    public Money(double balance)
    {
        this.balance = balance;
    }
    
    /**
     * Increases the balance by b amount, all doubles are valid
     * @param b the amount to increase the balance by
     */
    public void increase(double b)
    {
        balance += b;
    }

    /**
     * Decreases the balance by b amount, all doubles are valid
     * @param b the amount to decrease the balance by
     */
    public void decrease(double b)
    {
        balance -= b;
    }

    /**
     * get method for the balance field
     * @return the balance field
     */
    public double getBalance()
    {
        return balance;
    }
}
