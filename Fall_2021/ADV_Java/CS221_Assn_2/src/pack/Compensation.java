package pack;

import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_HALF_UP;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * A Compensation class. It defines objects that represent the two standard 
 * types of compensation.  
 * @author Matthew Yackiel
 */

public class Compensation implements Comparable<Compensation>
{
    private final BigDecimal rate; // The rate yearly if isSalary true, hourly otherwise
    private final boolean isSalary; // Determines the type of compensation
    
    /**
     * Initializes a Compensation object, with each field customized
     * @param rate either the Salary per year, or the hourly pay rate
     * @param isSalary True = a salaried position, False = an hourly position
     * Use:  Compensation c = new Compensation(rate, isSalary)
     *          Fields: c.rate = new BigDecimal(rate), c.isSalary = isSalary.
     */
    public Compensation(double rate, boolean isSalary)
    {
        this.rate = new BigDecimal(rate);
        this.isSalary = isSalary;
    }

    /**
     * Converts a hourly compensation object to a salary compensation object
     * @return a new Compensation object representing the change to salary
     *          if it is already hourly the calling object is returned
     * Use:  c = c2.hourlyToSalary(); c & c2 are Compensation objects
     */
    public Compensation hourlyToSalary()
    {
        if (isSalary) // return the object unhcanged
            return this;
        // Convert the rate to salary = 52 * 40 * rate, and swap the isSalary
        return new Compensation
        (rate.multiply(new BigDecimal(52*40)).doubleValue(), !isSalary);
    }
    
    /**
     * Converts a Salary compensation object to a hourly compensation object
     * @return a new Compensation object representing the change to hourly
     *          if it is already salary the calling object is returned
     * Use: c = c2.salaryToHourly(); c & c2 are Compensation objects
     */
    public Compensation salaryToHourly()
    {
        double conversion = 52*40;
        if (!isSalary) // return the object unchanged
            return this;
        return new Compensation // return a new object with the appropriate converion
                (this.rate.divide(new BigDecimal(conversion), 
                        2, ROUND_HALF_UP).doubleValue(), !isSalary);
    }
    
    /**
     * Calculates and returns the employee's gross biweekly paycheck.
     * @return a biweekly paycheck based on the type of compensation and rate
     * Use:  BigDecimal d = c.paycheck();  c is a Compensation object
     *          d represents the biweekly paycheck generated from this method.
     */
    public BigDecimal paycheck()
    {
        if (isSalary) // Salaried pay
            return this.rate.divide(new BigDecimal(26), 2, ROUND_HALF_UP);
        return this.rate.multiply(new BigDecimal(80)); // hourly pay
    }

    /**
     * @return the rate field of the given Compensation object
     * Use;  BigDecimal d = c.getRate();  c is a Compensation object.
     */
    public BigDecimal getRate() 
    {
        return rate;
    }

    /**
     * @return the isSalary field of the given Compensation object
     * Use:  boolean isSal = c.getIsSalary(); c is a Compensation object
     */
    public boolean isSalary() 
    {
        return isSalary;
    }
    
    /**
     * Compares 2 compensation objects
     * @param other the compensation being compared to
     * @return if value is negative, other is more per year than this object
     *         if value is 0, other is equal per year to this object
     *         if value is positive, other is less per year than this object
     * Use:  int i = c.compareTo(c2);  c & c2 are compensation objects.
     */
    @Override
    public int compareTo(Compensation other) 
    {
        if(this.equals(other)) // they are the same object
            return 0;
        // Normalized the type of payment for an accurate comparison
        Compensation one = this.salaryToHourly().hourlyToSalary(); 
        Compensation two = other.salaryToHourly().hourlyToSalary();
        
        return one.rate.subtract(two.rate).signum();
    }

    /**
     * @return a hashcode based on the objects field values.
     * Use:  int hash = c.hashcode() c is a Compensation object
     */
    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.rate);
        hash = 53 * hash + Objects.hashCode(isSalary);
        return hash;
    }

    /**
     * @param obj any object
     * @return Compensation this == Compensation obj;
     * Use:   if (c.equals(o)) return true;  c is Compensation object, o is any Object.
     */
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        if (obj == null) 
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Compensation other = (Compensation) obj;
        if (this.isSalary != other.isSalary) 
            return false;
        return Objects.equals(this.rate, other.rate);
    }
    
    /**
     * @return A string representation of a Compensation object's fields.
     * Use:  System.out.println("This is a Compensation object: " + c); 
     *          c is a Compensation object.
     */
    @Override
    public String toString()
    {
        return "Compensation{" + "rate=" + NumberFormat.getCurrencyInstance().format(rate) + ", isSalary=" + isSalary + '}';
    }

    public static void main(String[] args) {
        double sal = 50_000.00;
        double hour = 15.00;
        boolean isSalary = true;
        boolean isHourly = false;
        
        Compensation salary = new Compensation(sal, isSalary);
        Compensation hourly = new Compensation(hour, isHourly);
        
        if(salary.equals(hourly))
            System.out.println("salary == hourly");
        else
            System.out.println("salary != hourly");
        if(salary.equals(salary))
            System.out.println("salary == salary");
        
        System.out.println("compareTo1: " + salary.compareTo(hourly));
        System.out.println("compareTo2: " + hourly.compareTo(salary));
        System.out.println("compareTo3: " + salary.compareTo(salary));
        
        System.out.println("\nsalaryToHourly: " + salary.salaryToHourly() + "\n");
        System.out.println("hourlyToSalary: " + hourly.hourlyToSalary() + "\n");
        
        System.out.println("Your paycheck is: " + NumberFormat.getCurrencyInstance().format(salary.paycheck()) + " (salaried employee)");
        System.out.println("Your paycheck is: " + NumberFormat.getCurrencyInstance().format(hourly.paycheck()) + " (hourly employee)");
        
        System.out.println("The salaried hashcode: " + salary.hashCode());
        System.out.println("The hourly hashcode: " + hourly.hashCode());
       
        System.out.println("This compensation pays " + salary.getRate() + 
                (salary.isSalary() ? " per year." : " per hour"));
    }
}
