package inclasscode.generics;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author CJones
 */
public class Exercise1 {
      
    public static <T extends Integer>  int countOdds(Collection<T> collection){   
        int count=0;
        for(T t: collection){
            if(odd(t))count++;
        }
        return count; 
    }

    private static <T extends Integer> boolean odd(T t) {
        if(t/2 * 2 !=t) return true; 
        return false;
    }
    
    private static <T extends Integer> boolean even(T t) {
        if(t==0) return false; 
        return (t%2 ==0) ;
    }
    
    public static void main(String[] args){
        Collection <Integer> myCollection = new ArrayList<>();
        for (int i=0; i<26; i++) myCollection.add(i);
        System.out.println("The number of odd numbers is "+ countOdds(myCollection));
    }
}
