
package cs3.review_code;

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
    
    /*  These methods were added to help the currency class 
        We don't want side effects with these methods. They are not defined to change 
        the state of the current object
    */
    public Money add (Money amount){
       return new Money(this.amount.add(amount.getAmount()));
    }
    public Money duplicate (int times){
       return new Money(this.amount.multiply(new BigDecimal(times)));
    }
    public Money multiply (Money amount){
        return new Money(this.amount.multiply(amount.getAmount()));
    }
    
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

    
    public static void main(String args[]){
        
        double amount = 33.33;
        Money myMoney = new Money (amount);
        // Normal tests here
        
        //test for why we can't use double precision goes here 
        for (int i = 1; i<1000000001; i++){
            amount += 0.33;
            myMoney.earn(0.33);
        }
        
        System.out.println("Double precision is "+ NumberFormat.getCurrencyInstance().format(amount));
        System.out.println("BigDecimal precision is "+ myMoney);
    }

}
