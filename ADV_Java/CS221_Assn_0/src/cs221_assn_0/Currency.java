package cs221_assn_0;

/**
 * represents an amount of currency of s certain type.
 * ex. could represent a stack of 20 dollar bills, or 38 pennies.
 * Can only represent 1 type of currency.
 * @author Matthew Yackiel
 */
public class Currency
{
    private final String denomination; // the denomination
    private int count; // the amount of individual denominations

    
    /**
     * default constructor, denomination is set to "Dollars", count set to 0
     */
    public Currency()
    {
        this("Dollars", 0);
    }

    /**
     * Initializes a Currency object, with given denomination and count
     * @param denomination the type of currency being stored
     * @param count the amount of said currency
     */
    public Currency(String denomination, int count)
    {
        this.denomination = denomination;
        this.count = count;
    }

    /**
     * Used to add an amount of currency of the same type
     * @param amount the count of currency added ex) 10 bills, or coins
     */
    public void increase(int amount)
    {
        count +=  amount;
    }

    /**
     * The amount to decrease our currency by.  If the amount decreased is more
     * than the current count, count is set to 0.
     * @param amount the amount of currency added
     */
    public void decrease(int amount)
    {
        if (amount > count) // the amount surpasses the count
        {
            System.out.println("You cannot have negative money!");
            return;
        }
        this.count -= amount;
    }
    
    /**
     * a setter method for the count, cannot set to negative value
     * @param count the amount of bills/coins on hand
     */
    public void setCount(int count)
    {
        if (count < 0) // setting count to negative value
        {
            System.out.println("You cannot have negative money!");
            return;
        }
        this.count = count;
    }

    /**
     * Returns a string representation of the amount of denominations on hand
     * ex)  "5 Dollars"
     * @return a string of the count, and the denomination seperated by space 
     */
    public String toString()
    {
        return count + " " + denomination;
    }

    public static void main(String[] args)
    {
        String type = "Quarters";
        int count = 10;

        Currency curr = new Currency(type, count);
        curr.increase(10);
        curr.decrease(4);
    }

}
