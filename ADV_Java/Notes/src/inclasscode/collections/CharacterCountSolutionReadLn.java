
package inclasscode.collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import utilities.PATHS;

/**
 *
 * @author COMPSCI 221 
 * @version Fall 2021
 */
public class CharacterCountSolutionReadLn {
    public static void main(String[] args) {
       testOfCharacterMethods(); // Try ideas to see if they work when determining your algorithm 
        // Data Structures first
        int[] characterCounts = new int[26];//Automatically has zero for initial value of each array entry. 
        
       // Open the input text file 
        String inputFilePath = PATHS.HOME_DIRECTORY+"/input.txt";
        File inputFile = new File(inputFilePath);
        
        try (Scanner scanner =  new Scanner(inputFile)){ 
            while (scanner.hasNextLine()){
                String input = scanner.nextLine();
                String[] words = input.split(" ");
                for(String word : words )
                for(int i=0; i<word.length();  i++){
                    char ch = word.charAt(i);
                    if(Character.isUpperCase(ch))ch = Character.toLowerCase(ch);
                    if (Character.isLowerCase(ch)){
                       int index = (int) ch - (int) 'a';
                       characterCounts[index]++;
                    }  
                }
            }  
  
       }catch (FileNotFoundException ex) {
            
       }
        //Now the output
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
