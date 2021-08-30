package cs221_assn_0;

import java.util.ArrayList;

public class Wallet
{
    private final static String denominations[] = { "Pennies", "Nickles", "Dimes", "Quarters", "Ones", "Fives", "Tens", "Twenties" };
    private ArrayList<Currency> money; // ArrayList holding the currency
    private Money balance;
    
    /**
     * default constructor, builds a wallet with a net balance of 0 dollars
     */
    public Wallet()
    {
        this(new Money(0.0));
    }
    
    /**
     * builds a wallet with the provided balance
     * @param balance the amount of money in the wallet
     */
    public Wallet(Money balance)
    {
        // initialize money in the wallet
        money = new ArrayList<>();
        for (String s : denominations)
            money.add(new Currency(s, 0));

        this.balance = new Money();
        addCurrency(balance.getBalance());
    }
    
    /**
     * Used to spend money from the wallet, voids if you try to spend more money
     * than is in the wallet
     * @param amount the amount to spend from the wallet
     */
    public void spend(double amount)
    {
        if (amount > balance.getBalance()) // if we are spending more than we have
        {
            System.out.println("You can't spend more than you have, voiding transaction!");
            return;
        }
        subtractCurrency(amount);
    }

    /**
     * Used to account for money earned to the wallet
     * @param amount the amount of money earned (added to the wallet)
     */
    public void earn(double amount)
    {
        addCurrency(amount);
    }

    /**
     * a helper method for adding currency to the wallet
     * @param b the total value of currency added
     */
    private void addCurrency(double b)
    {
        balance.increase(b);
        int remainder = 0;
        // this loop breaks down a total into the minimal number of denominations 
        // needed to sum the total
        while(b > .01) 
        {
            if(b >= 20) // 20 dollar bills
            {
                b -= 20;
                money.get(7).increase(1);
                continue;
            }
            if (b >= 10) // 10 dollar bills
            {
                b -= 10;
                money.get(6).increase(1);
                continue;
            }
            if(b >= 5) // 5 dollar bills
            {
                b -= 5;
                money.get(5).increase(1);
                continue;
            }
            if (b >= 1) // 1 dollar bills
            {
                b -= 1;
                money.get(4).increase(1);
                continue;
            }
            if(b >= .25) // quarters
            {
                b -= .25;
                money.get(3).increase(1);
                continue;
            }
            if (b >= .1) // dimes
            {
                b -= .1;
                money.get(2).increase(1);
                continue;
            }
            if(b >= .05) // nickles
            {
                b -= .05;
                money.get(1).increase(1);
                continue;
            }
            if (b >= .01) // pennies
            {
                b -= .01;
                money.get(0).increase(1);
                continue;
            }
        }
    }

    /**
     * a helper method for removing currency from the wallet
     * @param b the total value of the currency removed
     */
    private void subtractCurrency(double b)
    {
        balance.decrease(b);
        int remainder = 0;
        // This loop removes currency to match the total, starting with the
        // largest denominations and working down
        while(b > 0)
        {
            if(b >= 20) // 20 dollar bills
            {
                b -= 20;
                money.get(7).decrease(1);
                continue;
            }
            if (b >= 10) // 10 dollar bills
            {
                b -= 10;
                money.get(6).decrease(1);
                continue;
            }
            if(b >= 5) // 5 dollar bills
            {
                b -= 5;
                money.get(5).decrease(1);
                continue;
            }
            if (b >= 1) // 1 dollar bills
            {
                b -= 1;
                money.get(4).decrease(1);
                continue;
            }
            if(b >= .25) // quarters
            {
                b -= .25;
                money.get(3).decrease(1);
                continue;
            }
            if (b >= .1) // dimes
            {
                b -= .1;
                money.get(2).decrease(1);
                continue;
            }
            if(b >= .05) // nickles
            {
                b -= .05;
                money.get(1).decrease(1);
                continue;
            }
            if (b >= .01) // pennies
            {
                b -= .01;
                money.get(0).decrease(1);
                continue;
            }
        }
    }

    /**
     * Generates a string representation of the bills currencies held in the
     * wallet
     * @return the list of currencies
     */
    public String toString()
    {
        String sub = "";
        for (Currency c : money)
            sub += c + " : ";
        return sub;
    }

    public static void main(String[] args)
    {
        double balance = 15.58;
        Wallet w = new Wallet(new Money(balance));
        System.out.println(w);
    }
}
