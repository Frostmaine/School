
package inclasscode.collections;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import utilities.PATHS;

/**
 *
 * @author COMPSCI 221 
 * @version Fall 2021
 */
public class CharacterCountSolutionInputSreamReader {
    public static void main(String[] args) throws IOException {
       testOfCharacterMethods(); // Try ideas to see if they work when determining your algorithm 
        // Data Structures first
        int[] characterCounts = new int[26];//Automatically has zero for initial value of each array entry. 
        
       // Open the input text file 
        String inputFilePath = PATHS.HOME_DIRECTORY+"/input.txt";
        File inputFile = new File(inputFilePath);
 //       InputStreamReader inputStream = new InputStreamReader(new FileInputStream(inputFilePath));
        try (InputStreamReader inputStream = new InputStreamReader(new FileInputStream(inputFile));   ){ 
            int data = inputStream.read();
            while (data > -1){ 
                char ch = (char) data; 
                if(Character.isUpperCase(ch))ch = Character.toLowerCase(ch);
                if (Character.isLowerCase(ch)){
                   int index = (int) ch - (int) 'a';
                   characterCounts[index]++;
                }
                data = inputStream.read();
            }  
  
       }catch (FileNotFoundException ex) {
            
       }
       for (int i=0; i<26; i++){
           System.out.println("Character ["+(char) ((int)'a' +i) +"] occurred " + characterCounts[i]+" times" );
       }
    }

    
    
    
    private static void testOfCharacterMethods() {
               char ch = 'a';
       System.out.println("Character a is at spot " + (int) ch+" in the character table");
        ch='A';
        System.out.println("Character A is at spot " + (int) ch+" in the character table");
        if(Character.getType(ch)== Character.UPPERCASE_LETTER ) {
            System.out.println("Character " +ch+ " is uppercase");
        }
        else System.out.println("Character " +ch+ " is lowercase");
        ch=  Character.toLowerCase(ch);
        System.out.println(ch); 
        ch='+';
        ch=  Character.toLowerCase(ch);
        System.out.println(ch); 
        if(Character.getType(ch)== Character.UPPERCASE_LETTER ) {
            System.out.println("Character " +ch+ " is uppercase");
        }
        else System.out.println("Character " +ch+ " is lowercase");

    } // end of testOfCharacterMethods();
    
    
}
