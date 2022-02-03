package midterm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Compsci 221 -- Spring 2106 mid-term exam
 * @author  <Put your first and last name here>
 */
public class MidtermAnswer {

    
    public static void main(String[] args){
        //Set up the data structures
        List<Integer>  integerList ;
        List<Double>  doubleList  ;
        List<Character> characterList ;
       
        Integer[] integerArray = {1,2,3,4,5,4,3,2,1}; 
        Double [] doubleArray = {1.0,2.0,3.0,4.0,5.0,10.0,15.0, 25.5};
        Character []charArray = {'Z','z','a', 'b', 'c','d',
                                  'e','g','h','I','K'};

     //Put the elements from the three arrays into the three lists here
        integerList = Arrays.asList(integerArray);
        doubleList = Arrays.asList(doubleArray);
        characterList = Arrays.asList(charArray);
        
        System.out.println("The sum of doubleList is " + 
                            sumList(doubleList) );
        System.out.println("The sum of integerList is " + 
                            sumList(integerList));
       
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
    public static <T> void displayList(List<T> list, String nameOfList) {
        Iterator<T> it = list.iterator();
        System.out.print(nameOfList+" contains [ ");
        while (it.hasNext()){
            System.out.print(it.next()+", ");
        }
        System.out.println("]");
    }

// Put the code to display a generic list in reverse order here -- Question 2
    public static <T> void printReverseList(List<T> list) {
        if (list.isEmpty()) return;
        printReverseList(list.subList(1, list.size()));
        System.out.print(list.get(0)+", ");
    }
    
    public static <T> void displayReverseList(List<T> list, 
                                              String nameOfList){
        System.out.print(nameOfList+" in reverse order contains [ ");
        printReverseList( list);
        System.out.println("]");
    }
    
    
// Put the code to return the sum a List of java.lang.Number OR any   
// class  derived from Number as  a double -- Question 3
   public static <T extends Number> double sumList(List<T > list) {
        Iterator<T> it = list.iterator();
        double sum = 0; 
        while (it.hasNext()){
            sum+=it.next().doubleValue();
        }
        return sum;
    }


}
    
