
package inclasscode.collections;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import utilities.PATHS;
/**
 *
 * @author COMPSCI 221
 * @version Spring 2021
 */



public class WordCountSolutionMap
{
   public static void main(String[] args) {
      // create data structures 
        Map<String, WordCountClass> words = new TreeMap<>();
      // Open Input File
//        String inputFilePath = PATHS.HOME_DIRECTORY+"/DictionaryWords.txt";
        String inputFilePath = PATHS.HOME_DIRECTORY+"/input.txt";
        File inputFile = new File(inputFilePath);     

        // Write code to read a word and process it 
            
        try (Scanner scanner =  new Scanner(inputFile)){ // closes file automatically 
            while (scanner.hasNext()){
                //Process each word  
                 String startingString = scanner.next().toLowerCase();
                 StringBuilder word = new StringBuilder();
                 for(int i=0; i<startingString.length(); i++){
                   if(Character.isLetter(startingString.charAt(i)))
                       word = word.append( Character.toLowerCase(startingString.charAt(i)));
                   else if(startingString.charAt(i)=='\'') word = word.append( Character.toLowerCase(startingString.charAt(i)));
                   else if(word.length()> 0){
                       // we have a white space here and we already constructed a word
    //                   System.out.println(word);  
                       if (words.containsKey(word.toString())){
                           WordCountClass wordCount = words.get(word.toString());
                           wordCount.count++;
                       } else {
                           words.put(word.toString(), new WordCountClass(word.toString(),1));
                       }
                       word.delete(0,word.length());
                   } //End of if
                 }
                 //process the last word 
                 if(word.length()> 0){
                       // we have a white space here and we already constructed a word
    //                   System.out.println(word);  
                       if (words.containsKey(word.toString())){
                           WordCountClass wordCount = words.get(word.toString());
                           wordCount.count++;
                       }else {
                           words.put(word.toString(), new WordCountClass(word.toString(),1));
                       }
                       word.delete(0,word.length());
                 }
            }// end while scanner.hasNext()
        }catch (FileNotFoundException ex) {
        
        }
               
      // Output results
       displayMap(words);

   } // end main

      // display map content
   private static void displayMap(Map<String, WordCountClass> map) {     
      Set<String> keys = map.keySet(); // get keys

      // sort keys
      Set<String> sortedKeys = new TreeSet<>(keys);

      System.out.printf("%nMap contains:%nKey\t\tValue%n");

      // generate output for each key in map
      for (String key : sortedKeys)
         System.out.printf("%-10s%10s%n", key, map.get(key).count);
      
      System.out.printf(
         "%nsize: %d%nisEmpty: %b%n", map.size(), map.isEmpty());
   } 

} // end class WordCount

