package textbook.ch15;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class CharacterFileExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
    
    void readLargerTextFile(String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
       /*
        * The try-with-resources statement -- file will close automatically when file processing completes  
        * Any object that implements java.lang.AutoCloseable, which includes all objects 
        * which implement java.io.Closeable, can be used as a resource.
        */
        try (Scanner scanner =  new Scanner(path)){
            while (scanner.hasNextLine()){
                //process each line in some way
            }      
        }
    }
    
    void readLargerTextFileAlternate(String aFileName) throws IOException {
      Path path = Paths.get(aFileName);
      //The try-with-resources statement -- file will close automatically when file processing completes 
      /*
       * Any object that implements java.lang.AutoCloseable, which includes all objects 
       * which implement java.io.Closeable, can be used as a resource.
       */
      try (BufferedReader reader = Files.newBufferedReader(path)){
      String line = null;
      while ((line = reader.readLine()) != null) {//until input is empty get the next line
        //process each line in some way -- could break into tokens -- Tokenizer class
      }    
    } // end of try with resoruces statement 
  }
    
    
  final static String FILE_NAME = "C:\\Temp\\input.txt";
  final static String OUTPUT_FILE_NAME = "C:\\Temp\\output.txt";
  //We are about to set the characterset, I have not had the need to set an encloding. 
  final static Charset ENCODING = StandardCharsets.UTF_8;
  
  //For smaller files -- read or write a List of String values

  List<String> readSmallTextFile(String aFileName) throws IOException {
     Path path = Paths.get(aFileName);
     return Files.readAllLines(path, ENCODING);
  }
  
  void writeSmallTextFile(List<String> aLines, String aFileName) throws IOException {
    Path path = Paths.get(aFileName);
    Files.write(path, aLines, ENCODING);
  }


  void writeLargerTextFile(String aFileName, List<String> aLines) throws IOException {
    Path path = Paths.get(aFileName);
    try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
      for(String line : aLines){
        writer.write(line);
        writer.newLine();
      }
    }
  }
  
}
