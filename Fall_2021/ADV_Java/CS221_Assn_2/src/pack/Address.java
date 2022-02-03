package pack;

import java.util.Objects;

/**
 * This class defines a Street address.
 * @author Matthew Yackiel
 */
public class Address
{
    private final String street;
    private final String zip;
    private final String town;
    private final String state;
    
    /**
     * Initializes all the required information to define an Address object
     * @param street the street address
     * @param zip the area zipcode
     * @param town the local town
     * @param state the State of residence
     */
    public Address(String street, String town, String state, String zip)
    {
        this.street = street;
        this.zip = zip;
        this.town = town;
        this.state = state;
    }

    /**
     * Returns the street
     * @return 
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * @return the area zip code
     */
    public String getZip()
    {
        return zip;
    }

    /**
     * @return the local town or city
     */
    public String getTown()
    {
        return town;
    }

    /**
     * @return the State
     */
    public String getState()
    {
        return state;
    }
    
    /**
     * Returns a Sting representation of an Address object
     * @return the address as it would be written on a letter
     */
    @Override    
    public String toString() 
    {
        return "Address{" + "street=" + street + ", zip=" + zip + ", town=" 
                + town + ", state=" + state + '}';
    }

    /**
     * @return a hashcode based off the objects fields
     */
    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.street);
        hash = 59 * hash + Objects.hashCode(this.zip);
        hash = 59 * hash + Objects.hashCode(this.town);
        hash = 59 * hash + Objects.hashCode(this.state);
        return hash;
    }

    /**
     * @param obj the object being compared to
     * @return this == obj
     */
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) // the same object
            return true;
        if (obj == null) // the object is not initialized
            return false;
        if (getClass() != obj.getClass()) // make sure we are comparing to the proper type of object
            return false;
        // Compare fields for equality.
        final Address other = (Address) obj;
        if (!Objects.equals(this.street, other.street))
            return false;
        if (!Objects.equals(this.zip, other.zip))
            return false;
        if (!Objects.equals(this.town, other.town))
            return false;
        return Objects.equals(this.state, other.state);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String strt = "400 E. Second St.";
        String town = "Bloomsburg";
        String state = "PA";
        String zip = "17815-1301";
        
        // Testing the constructors
        Address required_cons = new Address(strt, town, state, zip);
        
        System.out.println("required_cons:\n" + required_cons);
        
        System.out.println("HashCode: " + required_cons.hashCode());
        System.out.println("Equals: " + required_cons.equals(required_cons));
        System.out.println("Not Equals: " + required_cons.equals(new Object()));
        // etc.
        
        // Testing the getters
        System.out.println("street: " + required_cons.getStreet());
        System.out.println("town: " + required_cons.getTown());
        System.out.println("state: " + required_cons.getState());
        System.out.println("zip: " + required_cons.getZip());
    }
    
}
