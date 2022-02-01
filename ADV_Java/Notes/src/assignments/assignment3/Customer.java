package assignments.assignments3;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author COMPSCI 221
 * @version 2021
 */
public class Customer extends Person implements Serializable{
    private static final long serialVersionUID = 1L;
    private int customerIdentificationKey; //Primary key
    private LocalDate customerSince;

    /**
     *
     * @param customerIdentificationKey
     * @param customerSince
     * @param name
     * @param birthdate
     */
    public Customer(int customerIdentificationKey, LocalDate customerSince, Name name, LocalDate birthdate) {
        super(name,birthdate);
        this.customerIdentificationKey = customerIdentificationKey;
        this.customerSince = customerSince;
    }

    public Customer() {
    }


    public int getCustomerIdentificationKey() {
        return customerIdentificationKey;
    }

    /**
     *
     * @param customerIdentificationKey
     */
    public void setCustomerIdentificationKey(int customerIdentificationKey) {
        this.customerIdentificationKey = customerIdentificationKey;
    }

    public LocalDate getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(LocalDate customerSince) {
        this.customerSince = customerSince;
    }

    @Override
    public String toString(){
        LocalDate birthdate = this.getBirthdate();
        LocalDate customerScience = this.getCustomerSince();
        return "Customer," +this.customerIdentificationKey+
                ","+this.getName().getLastName()+","+this.getName().getFirstName()+","
                + birthdate.getMonthValue()+"/"+birthdate.getDayOfMonth()+"/"
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

