package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Designed to help students understand the development and use of Generic Code. 
 * 
 * @author cjones
 */
public class GenericExamples {
    
    
    /**
     * This method prints an array of Objects. It uses inheritance to allow 
     * arrays of any type to be the actual parameter. Note that all classes
     * derive from Object and all classes have the toString method for this reason.  
     * This method places all the elements on the same line.
     * 
     * @param myArray The array of elements to display,
     */
    public static void printArray(Object[] myArray){
        for(Object element:myArray){
            System.out.printf("%s ",element);
        }  
        System.out.println();
    }
    
    /**
     * This method prints an array of a generic type T. It uses inheritance to allow 
     * arrays of any type to be the actual parameter. Note that all classes
     * have the toString method that converts an instance of any class to a string. 
     * This means that any type of object can be stored in the array, but all the 
     * objects in the array must come from the same base type T.
     * This method places all the elements in the array on the same line. 
     * 
     * @param myArray The array of elements to display,
     */

    public static <T> void printArray2(T[] myArray){ //Compiles the same as public static void printArray(Object[] myArray)
        for(T element:myArray){
            System.out.printf("%s ",element);
        } 
        System.out.println();
    }

    /** 
     * This method will sum the values of the Number objects in an List. 
     * The elements 
     * of the list must be of type Number or a class derived from Number
     * so we know that they implement the method doubleValue()
     * 
     * @param <T> The generic type of the List. T must extend Number (so T can be of type Number). 
     * @param myList The List object holding our Number values. 
     * @return 
     */
    public static <T extends Number> double sumList(List<T> myList){ // Note <T extends Number> and List<T> 
       double sum = 0;
       for(T element:myList ){
           sum+=element.doubleValue(); //Why we need an instance of Number
       }
       return sum;
    }

    
    public static  double sumList2(List<? extends Number> myList){
       double sum = 0;
       for(Number element:myList ){
           sum+=element.doubleValue();
       }
       return sum;
    }

    
    public static  double sumList3(List<Number> myList){ // try to call this sumList
       double sum = 0;
       for(Number element:myList ){
           sum+=element.doubleValue();
       }
       return sum;
    }
    
    public static void main(String [] args){
        Integer [] myIntArray = {1,2,3,4,5};
        Double [] myDoubleArray ={1.0,2.0};
        System.out.println("Printing Integer [] myIntArray = {1,2,3,4,5}; using  printArray(Object[] myArray)");
        printArray(myIntArray);
        System.out.println("Printing Integer [] myIntArray = {1,2,3,4,5}; using  printArray2(T[] myArray)");
        printArray2(myIntArray);

        System.out.println("Printing Double [] myDoubleArray ={1.0,2.0}; using  printArray2(T[] myArray)");
        printArray2(myDoubleArray);

        
        List<Double> myDoubleArrayList = Arrays.asList(myDoubleArray);
                
        /*  OR
        List<Double> myDoubleArrayList =  new ArrayList<>();// Notice the interface type List<T> on the left
        
        for (Double d : myDoubleArray){
          myDoubleArrayList.add(d); 
        }
        
        */
        
        
        System.out.println("sumList(myDoubleArrayList)  =  "+ sumList(myDoubleArrayList));
        System.out.println("sumList2(myDoubleArrayList) =  "+ sumList2(myDoubleArrayList));
       // System.out.println("sumList3(myDoubleArrayList) =  "+ sumList3(myDoubleArrayList));
    }
}
