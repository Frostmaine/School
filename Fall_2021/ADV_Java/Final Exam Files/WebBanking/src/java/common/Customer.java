
package common;

import java.io.Serializable;
import java.time.LocalDate;

/**

 */
public class Customer implements Serializable{
     private static final long serialVersionUID = 1L;
     private int customerIdentificationKey; //Primary key 
     private LocalDate customerSince; 
     private Name name;
     private Address address;
     private LocalDate birthdate; 


    public Customer(int customerIdentificationKey, LocalDate customerSince, Name name, Address address, LocalDate birthdate) {
        this.customerIdentificationKey = customerIdentificationKey;
        this.customerSince = customerSince;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
    }

    public Customer() {
    }
    
    public Customer(Customer cust)
    {
        this.customerIdentificationKey = cust.customerIdentificationKey;
        this.address = cust.address;
        this.birthdate = cust.birthdate;
        this.customerSince = cust.customerSince;
        this.name = cust.name;
    }
    
    public void setName(Name name){
        this.name = name;
    }
    public Name getName(){
        return this.name;
    }
    
    public void setAddress(Address address){
        this.address = address; 
    }
    public Address getAddress(){
        return this.address;
    }
    
    public int getCustomerIdentificationKey() {
        return customerIdentificationKey;
    }

    public void setCustomerIdentificationKey(int customerIdentificationKey) {
        this.customerIdentificationKey = customerIdentificationKey;
    }

    public LocalDate getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(LocalDate customerSince) {
        this.customerSince = customerSince;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString(){
        LocalDate birthdate = this.getBirthdate();
        LocalDate customerScience = this.getCustomerSince();
        return "Customer," +this.customerIdentificationKey+
                ","+this.getName().getLastName()+","+this.getName().getFirstName()+"," 
                +birthdate.getMonthValue()+"/"+birthdate.getDayOfMonth()+"/"
                + birthdate.getYear()+","+customerScience.getMonthValue()+"/"+customerScience.getDayOfMonth()
                +"/"+customerScience.getYear()+"\n"; 
    }
     

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.customerIdentificationKey;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        return this.customerIdentificationKey == other.customerIdentificationKey;
    }
}
