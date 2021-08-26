package pack;

/**
 * This Class represents a person's name.
 * @author Matthew Yackiel
 */
public class Name 
{
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String salutation;
    private final String suffix;
    
    /**
     * the default constructor, fields default to empty strings
     */
    public Name()
    {
        this("", "", "");
    }
    
    /**
     * Copies an object of type Name into a new object of type Name
     * @param name 
     */
    public Name(Name name)
    {
        this(name.firstName, name.lastName, name.middleName, name.salutation, name.suffix);
    }
    
    /**
     * The minimal information constructor, salutation and suffix are empty
     * @param first, the first name
     * @param last, the last name
     * @param middle, the middle name
     */
    public Name(String first, String last, String middle)
    {
        this(first, last, middle, "");
    }
    
    /**
     * Initializes the "required" data (first, last, middle)name and the salutation
     * @param first, the first name
     * @param last, the last name
     * @param middle, the middle name
     * @param salu, the salutation
     */
    public Name(String first, String last, String middle, String salu)
    {
        this(first, last, middle, salu, "");
    }
    
    /**
     * Initializes all fields
     * @param first, the first name
     * @param last, the last name
     * @param middle, the middle name
     * @param salu, the salutation
     * @param suf, the suffix
     */
    public Name(String first, String last, String middle, String salu, String suf)
    {
        firstName = first;
        lastName = last;
        middleName = middle;
        salutation = salu;
        suffix = suf;
    }
    
    /**
     * Returns the first name
     * @return a string representing the first name
     */
    public String getFirstName() 
    {
        return firstName;
    }
    
    /**
     * Returns the last name
     * @return a string representing the last name
     */
    public String getLastName() 
    {
        return lastName;
    }

    /**
     * Returns the middle name
     * @return a string representing the last name
     */
    public String getMiddleName() 
    {
        return middleName;
    }

    /**
     * Returns the salutation
     * @return a string representing the salutation
     */
    public String getSalutation() 
    {
        return salutation;
    }

    /**
     * Returns the suffix
     * @return a string representing the suffix
     */
    public String getSuffix() 
    {
        return suffix;
    }
    
    /**
     * Returns a String representation of the Name object
     * @return 
     */
    public String toString()
    {
        return salutation + " " + firstName + " " + middleName + " " + lastName
                + " " + suffix;
    }
    
    /**
     * The main method used for testing the above class's methods, and constructors.
     * @param args, command line arguments
     */
    public static void main(String[] args)
    {
        // test values
        String first = "Matthew";
        String middle = "Thomas";
        String last = "Yackiel";
        String salu = "Mr.";
        String suff = "Esq.";
        
        // Testing the constructors
        Name default_cons = new Name();
        Name first_cons = new Name(first, last, middle);
        Name second_cons = new Name(first, last, middle, salu);
        Name third_cons = new Name(first, last, middle, salu, suff);
        
        // Testing toString overload and output from constructors
        System.out.println("Default: " + default_cons);
        System.out.println("first: " + first_cons);
        System.out.println("second: " + second_cons);
        System.out.println("third: " + third_cons);
        
        // Copy constructor
        default_cons = first_cons;
        System.out.println("default_cons (after copy cons): " + default_cons);
        
        // Testing the getter methods
        System.out.println("getFirst: " + third_cons.getFirstName());
        System.out.println("getLast: " + third_cons.getLastName());
        System.out.println("getMiddle: " + third_cons.getMiddleName());
        System.out.println("getSalutation: " + third_cons.getSalutation());
        System.out.println("getSuffix: " + third_cons.getSuffix());
    }
    
}
