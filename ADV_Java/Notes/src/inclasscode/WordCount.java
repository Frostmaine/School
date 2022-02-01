
package inclasscode;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import utilities.PATHS;
/**
 *
 * @author COMPSCI 221
 * @version Spring 2021
 */



public class WordCount
{
   public static void main(String[] args) {
      // create data structures 
      
      // Open Input File
        String inputFilePath = PATHS.HOME_DIRECTORY+"/input.txt";
        File inputFile = new File(inputFilePath);     
    
        // Write code to read a word and process it 
            
        try (Scanner scanner =  new Scanner(inputFile)){ // closes file automatically 
            while (scanner.hasNext()){
                 String word = scanner.next().toLowerCase();
                //Process each word  
            }
        }catch (FileNotFoundException ex) {
        
        }
               
      // Output results

   } // end main


} // end class WordCount

