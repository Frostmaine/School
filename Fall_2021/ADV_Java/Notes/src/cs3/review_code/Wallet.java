package cs3.review_code;


/**
 *
 * @author cjones
 */
public class Wallet {
    Currency names[];
    int quantity[] ;
  //names and quantity are called parallel arrays. 

  public  Wallet(){
      names = Currency.values();//Where did .values() come from?
      quantity = new int[names.length]; // defaults to zero in each element 
  }
  
  public void receiveCurrency(Currency currency){
      if (currency == null) return; 
      receiveCurrency(1,  currency);
  }
  
  public void removeCurrency(Currency currency){
      removeCurrency(1,currency);
  }
  
  public void receiveCurrency(int number, Currency currency){
     if (currency == null) return; 
     int i = indexOf(currency);
     if (i < quantity.length&& i >=0 ){//overkill in this case, but covers all bases
         quantity[i]+=number; //What would quantity[i]=+ number; do?
         return;
     }
     else{
        // now what???? -- this is an exception
        throw new java.lang.IllegalArgumentException("Currency " + currency +" was not found, index was " + i);
     } 
  }
  
  public void removeCurrency(int number, Currency currency){
     if (currency == null) return; 
     int i = indexOf(currency);
     if (i >=0 && i < quantity.length){//overkill in this case, but covers all bases
         if(quantity[i] >= number){
              quantity[i]-=number;
              return;
         }
         else{
              // now what???? -- this is an exception
              throw new java.lang.IllegalArgumentException("We don't have any "+currency.getPluralName());
         }
     }
     else
         throw new java.lang.IllegalArgumentException("Currency " + currency +" was not found, index was " + i);
  }
  
  public int howMany(Currency currency){
     if (currency == null) throw new java.lang.IllegalArgumentException("Currency was null");
     return quantity[indexOf(currency)];//should do error checking here
  }
  
  //Helper method -- private method in a class  
  private int indexOf(Currency currency){
      if (currency == null) throw new java.lang.IllegalArgumentException("Currency was null");
      for(int i = 0; i<names.length; i++){
         if (names[i].equals(currency)){
                return i;
         }
       }
       return names.length;// not possible, but Java requires it. 
   }
    
 /*
  What about toString() -- yes, should be written
  What about hashCode() and equals() -- not needed
  Two wallets are not the same unless they are the same wallet. 
  Memory addresses works for hashCode in this case because unique for each wallet
  */

}
