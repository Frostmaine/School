

package assignments.assignment2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author cjones
 * @version 1
 */
public abstract class Employee extends Person implements Serializable{
     private static final long serialVersionUID = 1L;
     private int employeeIdentificationKey; 
     private LocalDate dateHired; 
     private Compensation compensation; 

        
    public Employee(Name name, Address address, LocalDate birthdate, int employeeIdentificationKey, LocalDate dateHired, Compensation compensation) {
        super(name,address,birthdate);
        this.employeeIdentificationKey = employeeIdentificationKey;
        this.dateHired = dateHired;
        this.compensation = compensation;
    }

    public Employee() {
        super();
    }
    
    public BigDecimal getUnitsWorked() {
        return getCompensation().getUnitsWorked();
    }

    public void setUnitsWorked(BigDecimal unitsWorked) {
          getCompensation().setUnitsWorked(unitsWorked);
    }
    abstract public BigDecimal computeCompensation();
    abstract public BigDecimal computeCompensation(BigDecimal unitsWorked);
            
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

    public Compensation getCompensation() {
        return compensation;
    }

    public void setCompensation(Compensation compensation) {
        this.compensation = compensation;
    }
}
