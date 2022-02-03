package pack;

import java.util.Objects;

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
     * The minimal information constructor, salutation and suffix are empty
     * @param first the first name
     * @param last the last name
     * @param middle the middle name
     */
    public Name(String first, String last, String middle)
    {
        this(first, last, middle, "");
    }
    
    /**
     * Initializes the "required" data (first, last, middle)name and the salutation
     * @param first the first name
     * @param last the last name
     * @param middle the middle name
     * @param salu the salutation
     */
    public Name(String first, String last, String middle, String salu)
    {
        this(first, last, middle, salu, "");
    }
    
    /**
     * Initializes all fields
     * @param first the first name
     * @param last the last name
     * @param middle the middle name
     * @param salu the salutation
     * @param suf the suffix
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
     * @return a string representing the first name
     */
    public String getFirstName() 
    {
        return firstName;
    }
    
    /**
     * @return a string representing the last name
     */
    public String getLastName() 
    {
        return lastName;
    }

    /**
     * @return a string representing the last name
     */
    public String getMiddleName() 
    {
        return middleName;
    }

    /**
     * @return a string representing the salutation
     */
    public String getSalutation() 
    {
        return salutation;
    }

    /**
     * @return a string representing the suffix
     */
    public String getSuffix() 
    {
        return suffix;
    }
    
    /**
     * @return a string representing the name, in the standard order.
     */
    @Override
    public String toString() {
        return "Name{" + "firstName=" + firstName + ", lastName=" + lastName + 
                ", middleName=" + middleName + ", salutation=" + salutation + 
                ", suffix=" + suffix + '}';
    }

    /**
     * @return the object's hashcode, based on firstName, and lastName.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.lastName);
        return hash;
    }

    /**
     * @param obj the object being compared to
     * @return this == obj
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Name other = (Name) obj;
        if (!Objects.equals(this.firstName, other.firstName)) return false;
        if (!Objects.equals(this.lastName, other.lastName)) return false;
        if (!Objects.equals(this.middleName, other.middleName)) return false;
        if (!Objects.equals(this.salutation, other.salutation)) return false;
        return Objects.equals(this.suffix, other.suffix);
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
        Name first_cons = new Name(first, last, middle);
        Name second_cons = new Name(first, last, middle, salu);
        Name third_cons = new Name(first, last, middle, salu, suff);
        
        // Testing toString overload and output from constructors
        System.out.println("first: " + first_cons);
        System.out.println("second: " + second_cons);
        System.out.println("third: " + third_cons);
        
        // Testing the getter methods
        System.out.println("getFirst: " + third_cons.getFirstName());
        System.out.println("getLast: " + third_cons.getLastName());
        System.out.println("getMiddle: " + third_cons.getMiddleName());
        System.out.println("getSalutation: " + third_cons.getSalutation());
        System.out.println("getSuffix: " + third_cons.getSuffix());
    }

}
