
package textbook.ch15;

import cs3.review_code.Money;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utilities.Debug;
import static utilities.FileUtilities.openFileByPath;
import static utilities.FileUtilities.saveFile;

/**
 *
 * @author cjones
 */
public class ObjectFileExample {
    public static final String fileName = "Money.obj";
    public static final String fileName2 = "Money2.obj";

    public static void main(String[] args) {
        //Use interface as the type of the declaration
        List<Money> allMoney = new ArrayList<>(10);  
        Money money; 
        //Create an ArrayList of Money
        for (int i = 0; i<10; i++){
           money = new Money (i * 125); 
           allMoney.add(money); 
        }
        System.out.println("Creating Output File");
        writeFile(allMoney);
        System.out.println("Reading Input File");
        List<Money> newMoney = readFile();
        // iterate through List 
        Iterator<Money> it = newMoney.iterator();
        int count = 0;
        while(it.hasNext()){
            Money money2 = it.next();
            System.out.println(++count + "  "+money2);
        }
        
        System.out.println("We had "+count+" Money objects.");
        
        System.out.println("Now we will read one Money object at a time");
        ObjectInputStream input = null;
        FileInputStream fis = null;
        File file = openFileByPath(fileName2);
        Money money3 = null;
        try { 
              fis = new FileInputStream(file);
            try {
                input = new ObjectInputStream(new BufferedInputStream(fis));//don't need buffering
                input = new ObjectInputStream(fis);
            } catch (IOException ex) {
                
            }
            //First get how many obejcts to read
            int n= input.readInt(); 
            for(int i = 1; i<= n; i++){
                  money3 = (Money) input.readObject();
                  System.out.println(i + "  "+money3);
            }
            System.out.println("While loop ended");
        } catch (FileNotFoundException ex) {
            Debug.println("Could not open the file " + file.getAbsolutePath()+" for input");
            System.exit(1);
        } catch (ClassNotFoundException ex) {
             Debug.println("Could not read the file " + file.getAbsolutePath());
            System.exit(1);
        } catch (IOException ex) {
            //End of file reached -- not clean
        }
        finally{
            if(input != null) try {
                input.close();
            } catch (IOException ex) {
                
            }
        }
        
        }

    private static void writeFile(List<Money> allMoney)  {
        ObjectOutputStream output = null;
        File file = null;
        //First write out the ArrayList with one writeObject statement
        try {
            file = saveFile(fileName); 
            FileOutputStream fos = new FileOutputStream(file);
            output = new ObjectOutputStream(new BufferedOutputStream(fos)); 
           // output = new ObjectOutputStream(fos); //not buffered
            output.writeObject(allMoney);
        } catch (IOException ex) {
            if(file != null)
                Debug.println("Could not write data to the file "+ file.getAbsolutePath());
            System.exit(1); 
        } finally {
            try {
               if (output != null) output.close();
            } catch (IOException ex) {
                
            }
        }
        //Now we will show how to write one object at a time. 
        try {
            file = saveFile(fileName2); 
            FileOutputStream fos = new FileOutputStream(file);
            output = new ObjectOutputStream(fos);
            //First write how many objects we have into the file
            output.writeInt(allMoney.size());
            Iterator<Money> it = allMoney.iterator();
            while(it.hasNext()){
                Money money2 = it.next();
                output.writeObject(money2); 
            }
            output.flush();
        } catch (IOException ex) {
            if(file != null)
                Debug.println("Could not write data to the file "+ file.getAbsolutePath());
            System.exit(1); 
        } finally {
            try {
               if (output != null) output.close();
            } catch (IOException ex) {
                
            }
        }
        
    }

    private static List<Money> readFile() {
        List<Money> money=null ;
        ObjectInputStream input = null;
        FileInputStream fis = null;
        File file = openFileByPath(fileName);
        try { 
              fis = new FileInputStream(file);
              input = new ObjectInputStream(fis);
              money = (List<Money>) input.readObject();
        } catch (FileNotFoundException ex) {
            Debug.println("Could not open the file " + file.getAbsolutePath()+" for input");
            System.exit(1);
        } catch (IOException | ClassNotFoundException ex) {
             Debug.println("Could not read the file " + file.getAbsolutePath());
            System.exit(1);
        }
        finally{
            if(input != null) try {
                input.close();
            } catch (IOException ex) {
                
            }
        }
          return money; 
    }

    
}
