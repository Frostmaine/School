package pack;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class represents an employee of a company.  How they are compensated as
 * well as their personal info. Objects of this class are immutable
 * Employees can:
 *      Get a pay raise.
 *      Change their address
 *      and change their name
 *      
 * @author Matthew Yackiel
 */
public class Employee
{
    private final int id;
    private final Name name;
    private final Address address;
    private final Compensation compensation;
    private final LocalDate birthday;
    private final LocalDate employee_since;
    
    /**
     * Initializes an employee object with all fields given
     * @param name the employee's name
     * @param compensation how the employee is compensated
     * @param address the employee's home address
     * @param birthday the employee's birthday
     */
    public Employee(Name name, Address address, Compensation compensation,
            LocalDate birthday)
    {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.compensation = compensation;
        this.employee_since = LocalDate.now();
        this.id = Math.abs(this.hashCode()); // a extremely likely unique value
    }
    
    /**
     * A private constructor used to maintain data integrity, and thread safety.
     * I want the class to be able to change any field, but not user's
     * @param name the employee's name
     * @param address the employee's address
     * @param compensation the employee's compensation
     * @param birthday the employee's birthday
     * @param employee_since when the employee was hired
     * @param id the employee's unique id
     */
    private Employee(Name name, Address address, Compensation compensation, 
            LocalDate birthday, LocalDate employee_since, int id)
    {
            this.name = name;
            this.address = address;
            this.compensation = compensation;
            this.birthday = birthday;
            this.employee_since = employee_since;
            this.id = id;
    }
    
    /**
     * Give an employee a pay raise
     * @param compensation the new compensation which must have a higher value 
     *                     than this.compensation
     * @return returns the calling object if it is not greater than the current
     *          compensation.  Otherwise returns a new Employee object reflecting
     *          reflecting the changed compensation
     */
    public Employee payRaise(Compensation compensation)
    {
        // null check
        if (this == null || compensation == null)
            return this;
        // Check if the change is valid
        if(compensation.compareTo(this.compensation) <= 0)
            return this;
        // return a new Employee object reflecting the change.
        return new Employee(name, address, compensation, birthday, 
                            employee_since, id);
    }
    
    /**
     * Change the address of an employee
     * @param address the new address of the employee
     */
    public Employee changeAddress(Address address)
    {
        // Null check
        if (this == null || address == null)
            return this;
        return new Employee(name, address, compensation, birthday, 
                            employee_since, id);
    }
    
    /**
     * Change the name of an employee
     * @param name the new name the employee will have
     */
    public Employee changeName(Name name)
    {
        // null and equals check
        if (this == null || name == null || this.name.equals(name))
            return this;
        return new Employee(name, address, compensation, birthday, 
                            employee_since, id);
    }

    /**
     * @return the employee's unique id
     */
    public int getId() 
    {
        return id;
    }

    /**
     * @return the employee's name
     */
    public Name getName() 
    {
        return name;
    }

    /**
     * @return the employee's address
     */
    public Address getAddress() 
    {
        return address;
    }
    
    /**
     * @return how the employee is compensated
     */
    public Compensation getCompensation() 
    {
        return compensation;
    }

    /**
     * @return the employee's birthday
     */
    public LocalDate getBirthday() 
    {
        return birthday;
    }

    /**
     * @return how long the employee has worked here.
     */
    public LocalDate getEmployee_since() 
    {
        return employee_since;
    }

    /**
     * Generates a hashcode based off of all fields, except id.
     * @return a hashcode of the Employee object
     */
    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.address);
        hash = 61 * hash + Objects.hashCode(this.birthday);
        return hash;
    }

    /**
     * Determines if this == obj
     * @param obj the object being compared to
     * @return  this == obj
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
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.name, other.name)) 
            return false;
        if (!Objects.equals(this.address, other.address)) 
            return false;
        if (!Objects.equals(this.birthday, other.birthday)) 
            return false;
        return Objects.equals(this.employee_since, other.employee_since);
    }

    /**
     * @return a string representation of this object
     */
    @Override
    public String toString() 
    {
        return "Employee{" + "id=" + id + ", name=" + name + ", address=" + 
                address + ", compensation=" + compensation + ", birthday=" + 
                birthday + ", employee_since=" + employee_since + '}';
    }
    
    public static void main(String[] args) 
    {
        Employee emp = new Employee(new Name("Matthew", "Yackiel", "Thomas"), 
                new Address("557 Clints Lane", "Bloomsburg", "PA", "17815"),
                new Compensation(30_000, true), LocalDate.of(1997, 3, 31));
        
        // The initial object
        System.out.println(emp);
        
        
        // Check pay raise method
        Compensation pay_raise = new Compensation(31000, true);
        Compensation pay_drop = new Compensation(29000, true);
        emp = emp.payRaise(pay_raise);
        System.out.println(emp);
        emp = emp.payRaise(pay_drop);
        System.out.println(emp);
        
        // Getters
        System.out.println("Name: " + emp.getName());
        System.out.println("Address: " + emp.getAddress());
        System.out.println("Birthday: " + emp.getBirthday());
        System.out.println("Compensation: " + emp.getCompensation());
        System.out.println("Employee_Since: " + emp.getEmployee_since());
        System.out.println("Employee Id: " + emp.getId());
        
        
    }
}
