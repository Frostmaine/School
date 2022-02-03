
package assignments.assignment2;

import java.io.Serializable;

/**
 * The <code> Address </code> class represents a physical address. 
 * 
 */
public final class Address implements  Serializable{
     private static final long serialVersionUID = 1L;
     private String addressLine1;
     private String addressLine2;
     private String city;
     private String state;
     private String zipCode;


    public Address(String addressLine1, String addressLine2, String city, String state, String zipCode) {
        
        setAddressLine1(addressLine1);
        setAddressLine2(addressLine2);
        setCity(city);
        setState (state);
        setZipCode (zipCode);
        
    }
 

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1; 
        if(addressLine1 != null) this.addressLine1 = addressLine1.trim();
        
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        if(addressLine2 != null) this.addressLine2 = addressLine2.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        if(city != null) this.city = city.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        if(state != null) this.state = state.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
        if(zipCode != null) this.zipCode = zipCode.trim();
    }

    
     @Override
    public String toString(){
        StringBuilder address = new StringBuilder();
         address.append(this.addressLine1 + "\n");
         if (this.addressLine2 != null && this.addressLine2 !="") address.append(this.addressLine2 + "\n");
         address.append(this.city + ", "+this.state + " " + this.zipCode +"\n");
        
        return address.toString();
    }


    
}
