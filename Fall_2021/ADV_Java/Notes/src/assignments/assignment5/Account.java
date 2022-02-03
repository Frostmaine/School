package assignments.assignment5;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class Account implements Serializable {
     private static final long serialVersionUID = 1L;
     private int accountNumber; //This accounts unique identification number
     private int owner; //The customerId of the owner of this account
     private Money accountBalance; 
     
     // you add methods and constructors as needed

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.accountNumber;
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
        final Account other = (Account) obj;
        if (this.accountNumber != other.accountNumber) {
            return false;
        }
        return true;
    }
     
     
}
