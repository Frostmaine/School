
package common;

import java.io.Serializable;

/**
 * The <code> Address </code> class represents a physical address. 
 * 
 */
public final class Address implements  Serializable{
     private static final long serialVersionUID = 1L;
     int addressID;
     private String streetAddress;
     private String city;
     private String state;
     private String zipCode;

    public Address(){
    
    }
    public Address(String addressLine1, String city, String state, String zipCode) {
        
        setStreetAddress(addressLine1);
        setCity(city);
        setState (state);
        setZipCode (zipCode);
        
    }
    public Address(int addressID, String addressLine1, String city, String state, String zipCode) {
        setAddressID(addressID);
        setStreetAddress(addressLine1);
        setCity(city);
        setState (state);
        setZipCode (zipCode);
        
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }
 

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String addressLine1) {
        this.streetAddress = addressLine1; 
        if(addressLine1 != null) this.streetAddress = addressLine1.trim();
        
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
         address.append(this.streetAddress).append("\n");
         address.append(this.city).append(", ").append(this.state).append(" ").append(this.zipCode).append("\n");
        
        return address.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.addressID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (this.addressID != other.addressID) {
            return false;
        }
        return true;
    }


    
}
