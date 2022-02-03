
package inclasscode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import utilities.PATHS;

/**
 *
 * @author Compsci 221
 * @version Fall 2021
 */
public class CharacterCount {
        public static void main(String[] args) {
            int[] characterCounts = new int[26];

            // Open the input text file 
             String inputFilePath = PATHS.HOME_DIRECTORY+"/input.txt";
             File inputFile = new File(inputFilePath);

             try (Scanner scanner =  new Scanner(inputFile)){ 
               
                while(scanner.hasNext()){
                    String word = scanner.next().toLowerCase();
                    for(int i=0; i<word.length(); i++){
                        char ch = word.charAt(i);
                        if(Character.isLetter(ch)){
                            int index = (int)ch -(int)'a';
                            characterCounts[index]++; 
                        }
                    }//end of for loop
                } //end of scanner.hasNext()
                System.out.println( "Character \t Frequency " );
                for(int i = 0; i<26; i++){
                    System.out.println((char)(i+ (int)'a')+  "\t" + characterCounts[i] );
                }



              }catch (FileNotFoundException ex) {

              }

        }


    

    
    
}
