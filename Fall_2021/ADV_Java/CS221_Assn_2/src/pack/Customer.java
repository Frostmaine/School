package pack;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class stores all of the data that defines a customer. 
 * Customer's can change their Name, and address.
 * @author Matthew Yackiel
 */
public class Customer extends Person
{
    private final int id;
    private final LocalDate customer_since;

    /**
     * The minimal constructor for a customer.
     * @param name the name of the customer
     * @param address the customer's address
     * @param birthday the customer's birthday
     */
    public Customer(Name name, Address address, LocalDate birthday)
    {
        super(name, address, birthday);
        customer_since = LocalDate.now();
        id = Math.abs(this.hashCode());
    }
    
    /**
     * A private constructor used internally.
     * @param name the name of the customer
     * @param address the address of the customer
     * @param birthday the customer's birthday
     * @param customer_since how long the customer has been a member
     * @param id the customer's unique id
     */
    private Customer(Name name, Address address, LocalDate birthday, 
            LocalDate customer_since, int id)
    {
        super(name, address, birthday);
        this.customer_since = customer_since;
        this.id = id;
    }

    /**
     * @return the customer's unique id
     */
    public int getId() 
    {
        return id;
    }
    
    /**
     * @return when the customer first joined
     */
    public LocalDate getCustomer_since() 
    {
        return customer_since;
    }
    
    /**
     * @return a hashcode of the object
     */
    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 97 * hash + super.hashCode();
        hash = 97 * hash + Objects.hashCode(this.customer_since);
        return hash;
    }

    /**
     * Checks if this == obj
     * @param obj the object being compared to
     * @return this == obj
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
        final Customer other = (Customer) obj;
        if (this.id != other.id)
            return false;
        return Objects.equals(this.customer_since, other.customer_since);
    }

    /**
     * @return a string representation of a Customer object
     */
    @Override
    public String toString() 
    {
        return "Customer{" + "id=" + id + ", customer_since=" + 
                customer_since + '}';
    }
}
