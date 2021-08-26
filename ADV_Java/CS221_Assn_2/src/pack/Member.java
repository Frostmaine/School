package pack;

import java.time.Duration;
import java.time.LocalDate;

/**
 *
 * @author frostmaine
 */
public class Member 
{
    
    private final int id;
    private final Name name;
    private final Address address;
    private final LocalDate birthday;
    private final LocalDate member_since;
    
    public Member()
    {
        this(0, new Name(), new Address());
    }
    
    public Member(int id, Name name, Address address)
    {
        this(id, name, address, LocalDate.now(), LocalDate.now());
    }
    
    public Member(int id, Name name, Address address, LocalDate birthday,
            LocalDate customer_since)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.member_since = customer_since;
    }

    public int getId() 
    {
        return id;
    }

    public Name getName() 
    {
        return name;
    }

    public Address getAddress() 
    {
        return address;
    }

    public LocalDate getBirthday() 
    {
        return birthday;
    }

    public LocalDate getMember_since()
    {
        return member_since;
    }
    
    public Duration getCustomer_Duration()
    {
        return Duration.between(member_since, LocalDate.now());
    }
    
    public boolean isBirthday()
    {
        return birthday.equals(LocalDate.now());
    }
    
    public boolean equals(Member member)
    {
        return id == member.id;
    }
    
    public String toString()
    {
        return "id: " + id + "\nname: " + name + "\naddress: " + address + 
                "\nbirthday: " + birthday + "\ncustomer_since: "
                + member_since;
    }
}
