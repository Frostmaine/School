package pack;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class stores all of the data that defines a customer. 
 * Customer's can change their Name, and address.
 * @author Matthew Yackiel
 */
public class Customer 
{
    private final int id;
    private final Name name;
    private final Address address;
    private final LocalDate birthday;
    private final LocalDate customer_since;

    /**
     * The minimal constructor for a customer.
     * @param name the name of the customer
     * @param address the customer's address
     * @param birthday the customer's birthday
     */
    public Customer(Name name, Address address, LocalDate birthday)
    {
        
        customer_since = LocalDate.now();
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        id = Math.abs(this.hashCode());
    }
    
    /**
     * A private constructor used internally.
     * @param name the name of the customer
     * @param address the address of the customer
     * @param birthday the cusotmer's birthday
     * @param customer_since how long the customer has been a member
     * @param id the customer's unique id
     */
    private Customer(Name name, Address address, LocalDate birthday, 
            LocalDate customer_since, int id)
    {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.customer_since = customer_since;
        this.id = id;
    }
    
    /**
     * Change the address of an employee
     * @param address the new address of the customer
     */
    public Customer changeAddress(Address address)
    {
        // Null check
        if (this == null || address == null)
            return this;
        return new Customer(name, address, birthday, 
                            customer_since, id);
    }
    
    /**
     * Change the name of a customer
     * @param name the new name the customer will have
     */
    public Customer changeName(Name name)
    {
        // null and equals check
        if (this == null || name == null || this.name.equals(name))
            return this;
        return new Customer(name, address, birthday, 
                            customer_since, id);
    }
    
    /**
     * @return the customer's unique id
     */
    public int getId() 
    {
        return id;
    }

    /**
     * @return the customer's name
     */
    public Name getName() 
    {
        return name;
    }

    /**
     * @return the customer's address
     */
    public Address getAddress() 
    {
        return address;
    }

    /**
     * @return the customer's birthday
     */
    public LocalDate getBirthday() 
    {
        return birthday;
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
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.address);
        hash = 97 * hash + Objects.hashCode(this.birthday);
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
        if (!Objects.equals(this.name, other.name))
            return false;
        if (!Objects.equals(this.address, other.address))
            return false;
        if (!Objects.equals(this.birthday, other.birthday))
            return false;
        return Objects.equals(this.customer_since, other.customer_since);
    }

    /**
     * @return a string representation of a Customer object
     */
    @Override
    public String toString() 
    {
        return "Customer{" + "id=" + id + ", name=" + name + ", address=" + 
                address + ", birthday=" + birthday + ", customer_since=" + 
                customer_since + '}';
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Customer cust = new Customer(new Name("Matthew", "Yackiel", "Thomas"),
                                     new Address("400 E 2nd Street", "Bloomsburg", "PA", "17815"),
                                     LocalDate.of(3, 31, 1997));
        
        System.out.println("Inital Cusomter: " + cust);
        
        System.out.println("hashCode: " + cust.hashCode());
        
        System.out.println("Equals: " + cust.equals(cust));
        System.out.println("Not Equals: " + cust.equals(new Object()));
        
        // Getters
        System.out.println("Customer_Since: " + cust.getCustomer_since());
        System.out.println("Customer ID: " + cust.getId());
        System.out.println("Name: " + cust.getName());
        System.out.println("Birthday: " + cust.getBirthday());
        System.out.println("Address: " + cust.getAddress());
    }
    
}
