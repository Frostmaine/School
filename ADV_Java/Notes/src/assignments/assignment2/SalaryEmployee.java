package assignments.assignment2;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author cjones
 */
public class SalaryEmployee extends Employee{


    /**
     *
     * @param name
     * @param address
     * @param birthdate
     * @param employeeIdentificationKey
     * @param dateHired
     * @param compensation
     */
    public SalaryEmployee(Name name, Address address, LocalDate birthdate, int employeeIdentificationKey, LocalDate dateHired, Compensation compensation){
       super( name,  address,  birthdate,  employeeIdentificationKey,  dateHired, compensation); 
    }
    
    
    @Override
    public BigDecimal computeCompensation() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public BigDecimal computeCompensation(BigDecimal unitsWorked) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
}
