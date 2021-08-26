package pack;

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
     * Default Constructor, initializes fields to empty strings
     */
    public Address()
    {
        this("", "", "", "");
    }
    
    /**
     * Copy constructor, initializes an object of type Address to the values of
     * another object of type Address
     * @param addr 
     */
    public Address(Address addr)
    {
        this(addr.street, addr.town, addr.state, addr.zip);
    }
    
    /**
     * Initializes all the required information to define an Address object
     * @param street
     * @param zip
     * @param town
     * @param state 
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
     * Returns the zip code
     * @return 
     */
    public String getZip()
    {
        return zip;
    }

    /**
     * Returns the town
     * @return 
     */
    public String getTown()
    {
        return town;
    }

    /**
     * Returns the State
     * @return 
     */
    public String getState()
    {
        return state;
    }
    
    /**
     * Returns a Sting representation of an Address object
     * @return 
     */
    public String toString()
    {
        return street + "\n" + town + " " + state + ", " + zip;
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
        Address default_cons = new Address();
        Address required_cons = new Address(strt, town, state, zip);
        
        System.out.println("default_cons: " + default_cons);
        System.out.println("required_cons:\n" + required_cons);
        
        // Testing the getters
        System.out.println("street: " + required_cons.getStreet());
        System.out.println("town: " + required_cons.getTown());
        System.out.println("state: " + required_cons.getState());
        System.out.println("zip: " + required_cons.getZip());
    }
    
}
