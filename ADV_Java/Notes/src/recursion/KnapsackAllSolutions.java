package recursion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author cjones
 */
public class KnapsackAllSolutions {
        //Can we fill the knapsack to exactly the target weight starting with the current item 
    public static Collection<Collection>  allSolutions;
    
    public static void saveSolution(Collection solution){
        Collection temp = new ArrayList<Collection> ();
        Iterator iterator = solution.iterator();
        while (iterator.hasNext()){
            temp.add(iterator.next());
        }
        //Why do I need the temp collection in Java?
        // What would happen had I coded allSolutions.add(solution)?
        allSolutions.add(temp); 
        //Why can't  I clear temp here? 
    }
    
    public static void knapSackAllSolutions(int currentItem, int weights[], int target, Collection solution){
        
        if(target == 0) {// Successful base case must be first -- why? 
            //First Save Solution, then return 
            saveSolution(solution);
            return; // No need for boolean anymore. 
        } 
        if(target <0 || currentItem >= weights.length) return;//Not a legal solution
        
        //Two cases, this currentItem is either in the knapsack or not 
        solution.add(currentItem); // the  currentItem is  placed in the knapsack - now it goes in the correct order 
        knapSackAllSolutions(currentItem+1,  weights,  target-weights[currentItem], solution);
        //Take out current item and try again
        solution.remove(currentItem);
        knapSackAllSolutions(currentItem+1,  weights,  target, solution);
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
        allSolutions = new ArrayList<Collection>();
        knapSackAllSolutions( 0, weights,  target,  solution);//no need for boolean any more
        System.out.println("We have "+allSolutions.size()+" Solutions");
        Iterator<Collection> iterator = allSolutions.iterator();
        
        while (iterator.hasNext()){
            printSolution(iterator.next());
        }
    }
}

