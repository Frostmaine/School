package cs3.review_code;

import java.text.NumberFormat;

/**
 *
 * @author cjones
 */
public enum Currency {
    Penny("Penny", 0.01),
    Nickle("Nickle", 0.05),
    Dime("Dime",0.10),
    Dollar("Dollar",1.00),
    Five("Five", 5.00),
    Ten("Ten",10.00),
    Twenty("Twenty",20.00);
    // ....
    private final String name;
    private final double amount;
    
    Currency(String name, double amount){
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
    
    public double getAmount(){
        return amount; 
    }
    
     /**
     * Return the Currency with the corresponding string value. 
     * If no match is found then null is returned. 
     *
     * @param currencyName The name of the enumeration.
     * @return Enumeration with that string value or null.
     */
    public static Currency getCurrency (String currencyName) {
        for (Currency c : Currency.values ()) {
            if (c.name.equals (currencyName)){
                return c;
            }
        }
        return null;
    }
    
}
