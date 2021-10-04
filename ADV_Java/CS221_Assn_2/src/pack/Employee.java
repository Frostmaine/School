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
public class Employee extends Person
{
    private final int id;
    private Compensation compensation;
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
        super(name, address, birthday);
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
            super(name, address, birthday);
            this.compensation = compensation;
            this.employee_since = employee_since;
            this.id = id;
    }
    
    /**
     * Give an employee a pay raise
     * @param compensation the new compensation which must have a higher value 
     *                     than this.compensation
     */
    public void payRaise(Compensation compensation)
    {
        // null check
        if (this == null || compensation == null)
            return;
        if (this.compensation.compareTo(compensation) >= 0)
            return;
        this.compensation = compensation;
    }
    
    /**
     * @return the employee's unique id
     */
    public int getId() 
    {
        return id;
    }
    
    /**
     * @return how the employee is compensated
     */
    public Compensation getCompensation() 
    {
        return compensation;
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
        hash = 61 * hash + super.hashCode();
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
        return Objects.equals(this.employee_since, other.employee_since);
    }

    /**
     * @return a string representation of this object
     */
    @Override
    public String toString() 
    {
        return "Employee{" + "id=" + id + "compensation=" + compensation 
                + ", employee_since=" + employee_since + '}';
    }
}
