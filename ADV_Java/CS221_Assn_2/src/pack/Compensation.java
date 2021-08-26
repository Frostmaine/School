package pack;

/**
 *
 * @author frostmaine
 */
class Compensation 
{
    private double pay;
    private boolean salary;
    
    public Compensation()
    {
        this(0.0);
    }
    
    public Compensation(double salary)
    {
        this(salary, true);
    }
    
    public Compensation(double pay, boolean salary)
    {
        this.pay = pay;
        this.salary = salary;
    }
    
    public boolean isSalary()
    {
        return salary;
    }
    
    public double payCheck()
    {
        if(isSalary())
            return pay / 26 * .72;
        return pay * 80 * .72;
    }
    
    public boolean equals(Compensation comp)
    {
        return this.pay == comp.pay && this.salary == comp.salary;
    }
    
    public String toString()
    {
        return salary ? ("You are payed $" + pay + " per year") : 
                ("You are payed $" + pay + " per hour");
    }
}
