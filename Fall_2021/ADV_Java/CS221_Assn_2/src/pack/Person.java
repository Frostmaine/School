package pack;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 
 * @author Matthew Yackiel
 */
public class Person 
{
    private Name name;
    private Address address;
    private LocalDate birthday;
    
    /**
     * @param name the name of the person
     * @param address the address of the person
     * @param birthday the person's birthday
     */
    public Person(Name name, Address address, LocalDate birthday)
    {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
    }
    
    /**
     * @param person the Person object being copied
     */
    public Person(Person person)
    {
        this(person.name, person.address, person.birthday);
    }

    /**
     * @return a hashcode based on the fields representing the object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.address);
        hash = 29 * hash + Objects.hashCode(this.birthday);
        return hash;
    }

    /**
     * @param obj the object being compared to
     * @return this == obj
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Person other = (Person) obj;
        if (!Objects.equals(this.name, other.name))
            return false;
        if (!Objects.equals(this.address, other.address))
            return false;
        return Objects.equals(this.birthday, other.birthday);
    }
    
    /**
     * @return a string representation of a person object
     */
    @Override
    public String toString()
    {
        return "Person{" + "name=" + name + ", address=" + address + 
                ", birthday=" + birthday + '}';
    }

    /**
     * @return the person's name
     */
    public Name getName() 
    {
        return name;
    }

    /**
     * @return the person's address
     */
    public Address getAddress() 
    {
        return address;
    }

    /**
     * @return the person's birthday
     */
    public LocalDate getBirthday() 
    {
        return birthday;
    }

    /**
     * Set the name field
     * @param name 
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Set the address field
     * @param address 
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Set the birthday field
     * @param birthday 
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
    
}
