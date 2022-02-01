package inclasscode.ch15;

import cs3.review_code.Money;
import static inclasscode.ch15.MoneyFileIO1Answer.moneyFilePath;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import utilities.PATHS;

/**
 * Write a program to create 10 random Money objects and write them out to 
 * a binary file. Then read them back in and display them to the user. 
 * 
 * @author < your name goes here > 
 */
public class MoneyFileIO {
    
    public static void main(String[] args){
     //File name to store Money objects
       String moneyFilePath = PATHS.HOME_DIRECTORY+"/money.obj";
       ObjectOutputStream output= null;
        try {
                File file = new File(moneyFilePath); 
                FileOutputStream fos = new FileOutputStream(file);
                output = new ObjectOutputStream(new BufferedOutputStream(fos)) ;      
            
                
                output.close();
        } // end of try statement 
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally{

        }
        
    } //End of main
    
    
    private static void generateAndWriteMoneyObjects(int N){
            ObjectOutputStream output= null;
            try {
                File file = new File(moneyFilePath); 
                FileOutputStream fos = new FileOutputStream(file);
                output = new ObjectOutputStream(new BufferedOutputStream(fos)) ;      
               
                
                //generate objects and write file here 
                
                
                
                output.flush();
                output.close();
            } // end of try statement 
            catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } finally{

            }
    } //end of generateAndWriteMoneyObjects(int N)
    
    
    
    
} //End of MoneyFileIO
