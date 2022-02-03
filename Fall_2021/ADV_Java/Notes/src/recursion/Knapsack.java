package recursion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author cjones
 */
public class Knapsack {
        //Can we fill the knapsack to exactly the target weight starting with the current item  
    public static boolean knapSack(int currentItem, int weights[], int target, Collection solution){
        
        if(target == 0) return true; // Successful base case must be first -- why? 
        if(target <0 || currentItem >= weights.length) return false;
        
        //Two cases, this currentItem is either in the knapsack or not 
        if(knapSack(currentItem+1,  weights,  target-weights[currentItem], solution)){
            solution.add(currentItem); // the  currentItem is  in the knapsack - goes in reverse order 
            return true; // we found a solution
        }
        else if(knapSack(currentItem+1,  weights,  target, solution)){
            // this  currentItem is NOT in the knapsack 
             return true;// we found a solution
        } 
        return false; // We did not find a solution    
    }
    public static void  printSolution(Collection solution){
        Iterator iterator = solution.iterator();
        System.out.print("[ ");
        while (iterator.hasNext()){
            System.out.print(iterator.next()+ ", ");
        }
        System.out.println("]");
    }
    public static void main(String args[]){
        int[] weights={1,2,3,3,4,4,4,5,5,6,6,6,7,7,8} ; // weights[i] holds the weight of item i;
        int target=53; // target weight
        Collection solution = new ArrayList (); // Keep track of the items in the knapsack
        if(knapSack( 0, weights,  target,  solution)){
          System.out.println("We did find a solution with the following items");
           printSolution (solution);
        }else {
           System.out.println("We did NOT find a solution");
        }
    }
}

