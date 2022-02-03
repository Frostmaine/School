package recursion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author cjones
 */
public class BackTracking {
   private  class CurrentState{
       //Define the current state
   }; 
   
   private class Move{
       
   };
   
    
   private void saveSolution(Collection<Move> solution){
       
   }
    
    private boolean foundSolution(CurrentState currentState ){
        return false; 
    }
    
    private boolean legalState(CurrentState currentState){
        return false;
    }
    
    private Queue<Move> getAllMoves(CurrentState currentState){
        return null;
    }
    
    private CurrentState applyMove(CurrentState currentState, Move move){
        return null;
    }
    
    public boolean findSolution( CurrentState currentState,  Collection<Move> currentSolution){
        
        if(foundSolution(currentState) ) {// Successful base case 
            
            saveSolution(currentSolution);
            return true; 
        } 
        if(!legalState(currentState)) return false;//Not a legal solution
        Queue<Move>  possibleMoves = getAllMoves(currentState);
        while (!possibleMoves.isEmpty()){
            Move move = possibleMoves.remove();
            currentSolution.add(move);
            CurrentState newState = applyMove(currentState, move);
            if (findSolution(newState, currentSolution)) return true; 
            currentSolution.remove(move);
        }
            
        return false; 
    }
}
    
