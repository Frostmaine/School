package pack;

import java.time.LocalDate;

/**
 *
 * @author frostmaine
 */
public class Customer extends Member
{
    private double credit;
    
    public Customer()
    {
        this(0, new Name(), new Address());
    }
    public Customer(int id, Name name, Address address)
    {
        this(0, new Name(), new Address(), LocalDate.now(), LocalDate.now(), 0.0);
    }
    public Customer(int id, Name name, Address address, LocalDate birthday,
            LocalDate customer_since, double credit)
    {
        super(id, name, address, birthday, customer_since);
        this.credit = credit;
    }
    
    public double payback(double pay)
    {
        double temp;
        if(pay > credit)
        {
            temp = pay - credit;
            credit = 0.0;
            return temp;
        }
        credit -= pay;
        return 0.0;
    }
    
    public void takeOnCredit(double credit)
    {
        this.credit += credit;
    }
    
    public double creditStatement()
    {
        return credit;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
    }
    
}
