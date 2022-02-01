
package assignments.assignment2;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 *
 * @author cjones
 * @version 1
 */
public class HourlyEmployee extends Employee {

    /**
     *
     * @param name
     * @param address
     * @param birthdate
     * @param employeeIdentificationKey
     * @param dateHired
     * @param compensation
     */
    public HourlyEmployee(Name name, Address address, LocalDate birthdate, int employeeIdentificationKey, LocalDate dateHired, Compensation compensation){
       super( name,  address,  birthdate,  employeeIdentificationKey,  dateHired, compensation); 
    }
    
    public BigDecimal getHoursWorked() {
        return super.getCompensation().getUnitsWorked();
    }

    public void setHoursWorked(BigDecimal hoursWorked) {
        super.getCompensation().setUnitsWorked(hoursWorked);
    }
    
    @Override
    public BigDecimal computeCompensation() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public BigDecimal computeCompensation(BigDecimal hoursWorked) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
}
