

package solutions.assignment3;


import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 *
 * @author COMPSCI 221
 * @version 2021
 */
public class Employee extends Person implements Serializable{
     private static final long serialVersionUID = 1L;
     private int employeeIdentificationKey; 
     private LocalDate dateHired; 

        
    public Employee(Name name, LocalDate birthdate, int employeeIdentificationKey, LocalDate dateHired) {
        super(name,birthdate);
        this.employeeIdentificationKey = employeeIdentificationKey;
        this.dateHired = dateHired;
    }

    public Employee() {
        super();
    }
    
            
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.employeeIdentificationKey;
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
        final Employee other = (Employee) obj;
       return (this.employeeIdentificationKey == other.employeeIdentificationKey);
    }

    public int getEmployeeIdentificationKey() {
        return employeeIdentificationKey;
    }

    public void setEmployeeIdentificationKey(int employeeIdentificationKey) {
        this.employeeIdentificationKey = employeeIdentificationKey;
    }

    public LocalDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(LocalDate dateHired) {
        this.dateHired = dateHired;
    }
    
    @Override
    public String toString(){
        LocalDate birthdate = this.getBirthdate();
        LocalDate dateHired = this.getDateHired();
        return "Employee," +this.employeeIdentificationKey+
                ","+this.getName().getLastName()+","+this.getName().getFirstName()+"," 
                +birthdate.getMonthValue()+"/"+birthdate.getDayOfMonth()+"/"
                + birthdate.getYear()+","+dateHired.getMonthValue()+"/"+dateHired.getDayOfMonth()
                +"/"+dateHired.getYear()+"\n"; 
    }


}
