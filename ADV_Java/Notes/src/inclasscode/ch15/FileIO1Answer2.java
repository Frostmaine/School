package inclasscode.ch15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.StringTokenizer;
import utilities.PATHS;

/**
 * This program is to read a text file (input.txt) and reverse every other word in the file. 
 * The output should be written to the console and a new file (output.txt)
 * Do not put punctuation marks at the beginning of a word. So test! 
 * reversed is tset!
 * 
 * @author < Place your name here >
 */

public class FileIO1Answer2 {
    
    public static void main(String[] args){
        // Open the input text file 
        String inputFilePath = PATHS.HOME_DIRECTORY+"/input.txt";
        // File inputFile = new File(inputFilePath);
       // Paths inputFile=  inputFilePath = new Paths(inputFilePath);
                 
        // Open the output text file
        String outputFilePath = PATHS.HOME_DIRECTORY+"/output.txt";
        File outputFile = new File(outputFilePath);
        
        // While we have lines of input 
        //   Begin 
        //     Read each line as a word break each line down to indiviudal words.  
        //       if it is an even wrod - reverse it, but check for a punciation characters
        //        output the word to the screen
        //         output the word to a file
        //        output new line character to console and file
        //     End Loop
        //Notice two resources below in Try with resoruces statement 
        try (BufferedReader reader = new BufferedReader( new FileReader (inputFilePath)); 
             Formatter output = new Formatter(outputFile)){
            StringBuilder outputLine = new StringBuilder();
            StringBuilder outputWord = new StringBuilder();
            int wordCount = 0;
            String inputLine;
            while ((inputLine = reader.readLine()) != null){
              StringTokenizer tokens = new StringTokenizer(inputLine);
              outputLine.delete(0, outputLine.length());//Remove words from the last input line. 
              while(tokens.hasMoreTokens()){
                  if(outputWord.length() > 0) outputWord.delete(0, outputWord.length());
                  wordCount++;
                  String word = tokens.nextToken();
                  if(word.length()>1 && wordCount % 2 ==0) {
                      //Even word longer than 1 character
                      outputWord.append(word);
                      outputWord.reverse();
                      int maxCount=outputWord.length();//Why do we need this counter?
                      while(punciation(outputWord.charAt(0)) && maxCount >0 ){
                          maxCount--;
                          char ch = outputWord.charAt(0);
                          outputWord.deleteCharAt(0);
                          outputWord.append(ch);
                      }
                      outputLine.append(outputWord.toString()).append(" ");
                  }
                  else {
                      //Odd word
                      outputLine.append(word).append(" ");
                  }

              }//End of while moreTokens
                output.format(outputLine.toString()+'\n'); 
                System.out.println(outputLine.toString());
                //System.out.println(outputLine.toString()+'\n');
                //output.format("\n");
            } //End of While hasNextLine     
        } catch (FileNotFoundException ex) {
            // What should we do -- let the user know what happened. 
            // System.out.println("We could not open one of your files.");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); 
        }   
    }

    private static boolean punciation(char charAt) {
        char[] punciationSymbols = {'.',';',':','?',',','!'}; 
        for (char ch : punciationSymbols){
            if (ch==charAt)return true;
        }
        return false;
    }
    
}
