package inclasscode.ch15;

import cs3.review_code.Money;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import utilities.Debug;
import utilities.PATHS;

/**
 * Write a program to create 10 random Money objects and write them out to 
 * a binary file. Then read them back in and display them to the user. 
 * 
 * @author < your name goes here > 
 */
public class MoneyFileIO1Answer {
    //File name to store Money objects
    public static String moneyFilePath = PATHS.HOME_DIRECTORY+"/money.obj";
   
    public static void main(String[] args){
     
      generateAndWriteMoneyObjects(10);
      List<Money> allMoney = readMoneyObjectsFromFile();
        for (Money money : allMoney) {
            System.out.println(money);
        }
    } //End of main

    private static void generateAndWriteMoneyObjects(int N){
        ObjectOutputStream output= null;
        try {
            File file = new File(moneyFilePath); 
            FileOutputStream fos = new FileOutputStream(file);
            output = new ObjectOutputStream(new BufferedOutputStream(fos)) ;      
            List<Money> allMoney = generateMoneyObject(N);
            output.writeObject(allMoney); 
            output.flush();
            output.close();
        } // end of try statement 
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally{  
        }

    }
    
    private static List<Money> generateMoneyObject(int N) {
          long seed =System.currentTimeMillis();
          SecureRandom random = new SecureRandom();
          random.setSeed(seed);
          List<Money> allMoney = new ArrayList<>();
          Money money;
          for(int i=0; i<N; i++){
             int value = random.nextInt();
             value = Math.abs( value % 27);
             money = new Money(value*11.5);
             System.err.println(money);
             allMoney.add(money);
          }
          return allMoney;
    }

    private static List<Money> readMoneyObjectsFromFile() {
        List<Money> allMoney=null ;
        ObjectInputStream input = null;
        FileInputStream fis = null;
        File file = new File(moneyFilePath); 
        try { 
              fis = new FileInputStream(file);
              input = new ObjectInputStream(fis);
              allMoney = (List<Money>) input.readObject();
        } catch (FileNotFoundException ex) {
            Debug.println(ex.getMessage());
            System.exit(1);
        } catch (IOException | ClassNotFoundException ex) {
             Debug.println(ex.getMessage());
            System.exit(1);
        }
        finally{
            if(input != null) try {
                input.close();
            } catch (IOException ex) {
               Debug.println(ex.getMessage()); 
            }
        }
         return allMoney; 
    }// end of readMoneyObjectsFromFile()
    
} //End of MoneyFileIO
