package inclasscode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import utilities.PATHS;


/**
 * This program is to read a text file (input.txt) and reverse every other word in the file. 
 * The output should be written to the console and a new file (output.txt)
 * Do not put punctuation marks at the beginning of a word. So test! 
 * reversed is tset!
 * 
 * @author < Place your name here >
 */
public class FileIOCompletedInClass {
    
    public static void main(String[] args) {
        // Open the input text file 
        String inputFilePath = PATHS.HOME_DIRECTORY+"/input.txt";
        File inputFile = new File(inputFilePath);
                 
        // Open the output text file
        String outputFilePath = PATHS.HOME_DIRECTORY+"/output.txt";
        File outputFile = new File(outputFilePath);
        
         try (Scanner scanner =  new Scanner(inputFile)){
            int wordCount = 0;
           StringBuilder outputLine = new StringBuilder(); 
            while (scanner.hasNextLine()){
              String inputLine = scanner.nextLine();
              String [] words = inputLine.split(" ");
              outputLine.delete(0, outputLine.length());
              for(int i = 0; i<words.length; i++){
                 wordCount++;
                 StringBuilder word = new StringBuilder(words[i]);
                 if(wordCount%2 ==0) {               
                     word.reverse();
                    
                 }
                 outputLine.append(word+" "); 
              }
              System.out.println(outputLine);
            }
              
  
       }catch (FileNotFoundException ex) {
            
       }
    }
}
