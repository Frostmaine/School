package cs3.review_code;

import java.text.NumberFormat;

/**
 *
 * @version 2021
 * @author cjones
 */
public enum Currency2 {
    Penny("Penny", new Money(0.01)),
    Nickle("Nickle", new Money(0.05)),
    Dime("Dime",new Money(0.10)),
    Quarter("Quarter",new Money(0.25)),
    Dollar("Dollar",new Money(1.00)),
    Five("Five", new Money(5.00)),
    Ten("Ten",new Money(10.00)),
    Twenty("Twenty",new Money(20.00));
    // ....
    private final String name;
    private final Money amount;
    
    Currency2(String name, Money amount){
        this.name = name;
        this.amount = amount;
    }
    
    @Override
    public String toString(){
     return "A "+name+ "is worth "+ NumberFormat.getCurrencyInstance().format(amount);    
    }
    
    public String getPluralName(){
        if(this == Penny) return "Pennies"; // Why does this work?
        return name+"s"; 
    }
    
    public String getName(){
        return name;
    }
    
    public Money getAmount(){
        return amount; 
    }
    
     /**
     * Return the Currency with the corresponding string value. 
     * If no match is found then null is returned. 
     *
     * @param currencyName The name of the enumeration.
     * @return Enumeration with that string value or null.
     */
    public static Currency2 getCurrency (String currencyName) {
        for (Currency2 c : Currency2.values ()) {
            if (c.name.equals (currencyName)){
                return c;
            }
        }
        return null;
    }
    
}
