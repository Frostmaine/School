package pack;

import java.time.LocalDate;

/**
 *
 * @author frostmaine
 */
public class Employee extends Customer
{
    private Compensation pay;
    
    public Employee()
    {
        this(new Compensation(), 0, new Name(), new Address());
    }
    
    public Employee(Compensation pay, int id, Name name, Address address)
    {
        this(pay, id, name, address, LocalDate.now(), LocalDate.now());
    }
    
    public Employee(Compensation pay, int id, Name name, Address address, 
            LocalDate birthday, LocalDate employee_since)
    {
        super(id, name, address, birthday, employee_since);
        this.pay = pay;
    }
    
    public Compensation getCompensation()
    {
        return pay;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        
    }
    
}
