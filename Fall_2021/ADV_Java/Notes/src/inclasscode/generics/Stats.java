
package inclasscode.generics;

import java.util.Arrays;

/**
 *
 * @author CS 3 Student
 */
public class Stats<T extends Number>{
    private T[] numbers; // Array of Numbers or a subclass 
    public Stats(T[] numbers){
        //Line 1 --CODE to Write
        this.numbers = Arrays.copyOf(numbers, numbers.length); // why copy? 

    }
    double average(){
      double sum = 0; 
      for(Number n : numbers) sum+=n.doubleValue(); 
        return sum / numbers.length;
    }
    
    boolean sameAverage(Stats<?> otherStats){//Any Stats class will do, all return a double for the average
        
        return average() == otherStats.average(); 
    }
    
    double maximumValue(){
        if (numbers.length <=0) throw new IllegalArgumentException("Our array is empty");
        double max = numbers[0].doubleValue();
        for(int i=1; i<numbers.length; i++)
           if (numbers[i].doubleValue() > max) max = numbers[i].doubleValue();
        return max;
    }
    
    
    public static void main(String [] args){
        Integer integerNumbers[] = {1,2,3,4,5,6};
        Double doubleNumbers[] = {1.0,2.0,3.0,4.0,5.0,6.0};
        Stats<Integer> integerStats = new Stats<>(integerNumbers);
        integerNumbers[5] = 0; 
        Stats<Integer> integerStats2 = new Stats<>(integerNumbers);
        Stats<Double> doubleStats = new Stats<>(doubleNumbers);
        boolean same = integerStats.sameAverage(doubleStats);
        boolean same2 = integerStats.sameAverage(integerStats2);
        System.out.println("same2 is " + same2);
         System.out.println("same2 is " + same2);
    }
    
}
