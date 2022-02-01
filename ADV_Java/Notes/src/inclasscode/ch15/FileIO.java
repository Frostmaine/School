package inclasscode.ch15;

import java.io.File;
import utilities.PATHS;


/**
 * This program is to read a text file (input.txt) and reverse every other word in the file. 
 * The output should be written to the console and a new file (output.txt)
 * Do not put punctuation marks at the beginning of a word. So test! 
 * reversed is tset!
 * 
 * @author < Place your name here >
 */
public class FileIO {
    
    public static void main(String[] args){
        // Open the input text file 
        String inputFilePath = PATHS.HOME_DIRECTORY+"/input.txt";
        File inputFile = new File(inputFilePath);
                 
        // Open the output text file
        String outputFilePath = PATHS.HOME_DIRECTORY+"/output.txt";
        File outputFile = new File(outputFilePath);
        
        
    }
    
}
