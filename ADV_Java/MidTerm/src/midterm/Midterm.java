

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Compsci 221 -- Fall 2021 mid-term exam
 * @author  Matthew Yackiel
 */
public class Midterm {

    
    public static void main(String[] args){
        //Set up the data structures
        List<Integer>  integerList ;
        List<Double>  doubleList ;
        List<Character> characterList ; 
       
        Integer[] integerArray = {1,2,3,4,5,4,3,2,1}; 
        Double [] doubleArray = {1.0,2.0,3.0,4.0,5.0,10.0,15.0, 25.5};
        Character []charArray = {'Z','z','a', 'b', 'c','d','e','g','h','I','K'};

       //Put the elements from the three arrays into the three lists here
        integerList = Arrays.asList(integerArray);
        doubleList = Arrays.asList(doubleArray);
        characterList = Arrays.asList(charArray);
        
        System.out.println("The sum of doubleList is " + sumList(doubleList) );
        System.out.println("The sum of integerList is " + sumList(integerList) );
       
       //Display each list
        displayList(doubleList, "doubleList");
        displayList(integerList, "integerList");
        displayList(characterList, "characterList");
        
        
        //Display each list in reverse order here
        displayReverseList(doubleList, "doubleList");
        displayReverseList(integerList, "integerList");
        displayReverseList(characterList, "characterList");
        
    }
    
// Put the code to display a generic list here -- Question 1 
    
    /**
     * This method outputs a list of objects of Type T. T being any object.
     * @param <T> any object
     * @param list the list of objects being output
     * @param name the name of the list
     */
    private static <T> void displayList(List<T> list, String name) {
        String out = name + " contains: ["; // note the name of the list
        for (T item: list) // loop the list
            out += item + ", ";
        out += "]"; // append square bracket
        System.out.println(out);
    }
    

// Put the code to display a generic list in reverse order here -- Question 2
    /**
     * This method prints the list of objects of Type T in reverse order. T being any object
     * @param <T> any object
     * @param list the list of objects being output
     * @param name the name of the list
     */
    private static <T> void displayReverseList(List<T> list, String name) {
        String out = name + " int reverse order contains: [";
        out += reverseList(list, list.size()) + "]";
        System.out.println(out);
    }
    
    private static <T> String reverseList(List<T> list, int size)
    {
        T t = list.get(size-1);
        if (size <= 1)
            return t.toString();
        return  t.toString() + reverseList(list, size-1);
    }
    
    
// Put the code to return the sum a List of java.lang.Number OR any  class derived from Number as  a double -- Question 3

    /**
     * 
     * @param <T>
     * @param list
     * @return 
     */
    private static <T extends Number> double sumList(List<T> list) {
        double sum = 0.0;
        for (T item: list)
            sum += item.doubleValue();
        return sum;
    }

    

   


}
    
