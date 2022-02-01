

package assignments.assignment2;

import java.math.BigDecimal;

/**
 *
 * @author cjones
 * @version 1
 */
public class Compensation {
    private CompensationType compensationType;
    private BigDecimal compensationRate; 
    private BigDecimal unitsWorked; 

    public Compensation(CompensationType compensationType, BigDecimal compensationRate) {
        if(compensationType==null) throw new IllegalArgumentException ("Compensation Type is null");
        this.compensationType = compensationType;
        if (compensationRate.longValue()<0) throw new IllegalArgumentException ("Compensation rate is negative $"+compensationRate);
        this.compensationRate = compensationRate;
    }

    public CompensationType getCompensationType() {
        return compensationType;
    }

    public void setType(CompensationType compensationType) {
        this.compensationType = compensationType;
    }

    public BigDecimal getCompensationRate() {
        return compensationRate;
    }

    public void setCompensationRate(BigDecimal compensationRate) {
        this.compensationRate = compensationRate;
    }
    
    public BigDecimal getUnitsWorked() {
        return unitsWorked;
    }

    public void setUnitsWorked(BigDecimal unitsWorked) {
        this.unitsWorked = unitsWorked;
    }

    
    public BigDecimal computeHourlyCompensation(BigDecimal hoursWorked){
        throw new UnsupportedOperationException("Not supported yet."); 
        //set units worked to 0 before returning???
      
    }
    public BigDecimal computeSalaryCompensation(){
       throw new UnsupportedOperationException("Not supported yet."); 
       //set units worked to 1 before returning
    }
    public BigDecimal computeSalaryCompensation(BigDecimal unitsWorked){
       throw new UnsupportedOperationException("Not supported yet."); 
       //set units worked to 1 before returning??
    }
    public BigDecimal computeCompensation(){
       throw new UnsupportedOperationException("Not supported yet."); 
       //set units worked to 1 before returning???
    }
    
    
}
