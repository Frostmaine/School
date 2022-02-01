package assignments.assignment5;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 
 * @author cjones
 * @version Fall 2014
 */

public class Money implements Serializable, Comparable<Money> {
    private BigDecimal amount = null;
    
    public Money(  ){
        amount = new BigDecimal(0);
    }
    
    public Money (double amount){
        this.amount = BigDecimal.valueOf(amount);
    }
    
    public Money(Money money){
        //amount = new BigDecimal(0).add(money.getAmount());
        this(money.getAmount());
    }
    
    private Money(BigDecimal amount){
        this.amount = amount; //note BigDecimal is immutable
    }
    
    public void earn (double amount){
      this.amount = this.getAmount().add(BigDecimal.valueOf(amount));
    }
    
    public void earn(Money amount){
        this.amount = this.amount.add(amount.amount);
    };
    public void spend(Money amount){
        this.amount = this.amount.subtract(amount.amount);
    };
    private BigDecimal getAmount(){
        return this.amount;
    }
    @Override
    public String toString(){
       return NumberFormat.getCurrencyInstance().format(amount);
    }

    @Override
    public int hashCode() {
      return amount.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Money otherMoney = (Money) obj;
        return this.amount.equals(otherMoney.amount );
    }
    
    
    
        @Override
    public int compareTo(Money otherMoney) {
        if(otherMoney==null ) return 1; // Greater than nothing?
        return this.amount.compareTo(amount);
    }

    
}