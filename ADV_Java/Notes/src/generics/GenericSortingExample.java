package generics;

import java.util.Comparator;

/**
 *
 * @author cjones
 */
public class GenericSortingExample {
    //Sort array{startIndex] to array[endIndex] inclusive 
    //compiles fine with <T extends Comparable> -- but use code below.
    public static <T extends Comparable<T>> void selectionSort(T[] array, int startIndex, int endIndex){
        for(int i = startIndex; i<endIndex; i++){
            int indexOfMinValue = findMin(array,i,endIndex);// find the min in array starting with location i;
            swap (array, i,indexOfMinValue);
        }
    }
    
    //compiles fine with <T extends Comparable> -- but use code below. 
   // Technique -- assume the first one is the min - compare current min with the rest 
    public static <T extends Comparable<T>> int findMin(T[] array,int startIndex, int endIndex ){
        int loc = startIndex; // assume the first one is min
        // now check the rest
        for (int i = startIndex+1; i<=endIndex; i++){
            if(array[i].compareTo(array[loc]) < 0)loc = i;
        }
        return loc; 
    }
    
    
    
    public static <T> void swap(T[] A, int i, int j){
        T temp = A[i];
        A[i] = A[j];
        A[j]=temp;
    }
    
    //Sort array{startIndex] to array[endIndex] inclusive  // Why not <T extends Comparable<T> for return type
    public static <T> void selectionSort(T[] array, int startIndex, int endIndex, Comparator<T> comparator){
        for(int i = startIndex; i<endIndex; i++){
            int indexOfBestValue = findMin(array,i,endIndex,comparator);// find the min in array starting with location i;
            swap (array, i,indexOfBestValue);
        }
    }
    
    // Why not <T extends Comparable<T> for return type below? 
    public static <T> int findMin(T[] array,int startIndex, int endIndex, Comparator<T> comparator ){
        int loc = startIndex; // assume the first one is "smallest"
        // now check the rest
        for (int i = startIndex+1; i<=endIndex; i++){
            if(comparator.compare(array[i], array[loc]) < 0)loc = i;
        }
        return loc; 
    }
    
    public class MyComparator implements Comparator{
//This method was auto completed by NetBeans
        @Override
        public int compare(Object o1, Object o2) {
           
            throw new UnsupportedOperationException("Not supported yet."); 
           //What would we need to do to write this code?
           // Double d1 = (Double)o1;
          //  Double d2 = (Double)o2;
          //  return d1.compareTo(d2);
           // Note: Some input files use unchecked or unsafe operations.
           // Note: Recompile with -Xlint:unchecked for details.
        }  
    }
    
    public class MyCamparator2 implements Comparator<Double>{
//This method was auto completed by NetBeans
        @Override
        public int compare(Double o1, Double o2) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    
    }
 
    public static class Greater implements Comparator<Double>{
//This method was started by NetBeans as above, then edited 
        @Override
        public int compare(Double o1, Double o2) {
            return - o1.compareTo(o2);
        }
    
    }
    
    public static class Greater2 <T extends Comparable<T>> implements Comparator<T>{
//This method was started by NetBeans as above, then edited 
       @Override
        public int compare(T v1, T v2) {
            return - v1.compareTo(v2);
        }


    
    }
    
    public static <T> void printArray(T[] myArray){
        for(T element:myArray){
            System.out.printf("%s ",element);
        }    
    }
    
    //Returns first index of an item in the array that is equals to item. 
    public static <T> int linearSearch(T[] myArray, T item){
         for(int i=0; i<myArray.length; i++){
            if (myArray[i].equals(item)) return i;
         }
         return myArray.length;
    }
            
            
    public static void main(String[] args){
         Integer [] myIntArray = {1,2,3,4,5,4,3,-15,18,-2,19,-15,2};
         Double [] myDoubleArray ={1.0,2.0, -5.0, 15.0, 3.0 -17.0, 11.4, -3.5, 13.2, -1.0};
         System.out.println("The Double array before sorting");
         printArray(myDoubleArray);
         System.out.println();
         //Greater greaterComparator =new Greater(); 
         Greater2<Double> greaterComparatorDouble= new Greater2<>();;//Diamond Notation
         Greater2<Integer> greaterComparatorInteger= new Greater2<Integer>();//not used
         selectionSort(myDoubleArray,0,myDoubleArray.length-1,greaterComparatorDouble);
         System.out.println("The Double array after sorting from largest to smallest");
         printArray(myDoubleArray);
         System.out.println();
         selectionSort(myDoubleArray,0,myDoubleArray.length-1);
         System.out.println("The Double array after sorting from smallest to largest");
         printArray(myDoubleArray);
         System.out.println();
 
    }
}
