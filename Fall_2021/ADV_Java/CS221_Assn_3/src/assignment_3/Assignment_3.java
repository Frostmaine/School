package assignment_3;

import assn2.classes.Employee;
import assn2.classes.Customer;
import assn2.classes.Name;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Matthew Yackiel
 */
public class Assignment_3 {

    public static void main(String[] args) {
        Set<Customer> customers = new HashSet<>();
        List<Employee> employees = new LinkedList<>();

        File inputFile = fileSelectInput(); // choose input file
        File objectFile = fileSelectObjectOutput(); // choose object file
        File textFile = fileSelectTextOutput(); // choose text output file
        
        readTextFile(customers, employees, inputFile);  // read the csv file

        writeTextFile(customers, employees, textFile); // store data in text file
        
        writeObjectFile(customers, employees, objectFile); // store the object data as a binary
        
        readObjectFile(customers, employees, objectFile);// read the collections from the binary file
        
        System.out.println("Object File");
        outputStandard(customers, employees); // output to standard output
        
        readTextFile(customers, employees, textFile); // read the entries from the text file
        
        System.out.println("Text File");
        outputStandard(customers, employees); // output to standard output
        
        // Sets cannot be sorted so we use a list as a proxy to sort the elements
        List<Customer> customerList = new ArrayList<>();
        customerList.addAll(customers);
        Collections.sort(customerList, new BirthdayComparator());
        for (Customer c : customerList)
            System.out.print(c);
        System.out.println("");
    }

    //Some helper methods that make to above code more readable
    
    /**
     * Add a customer to the set of customers. does nothing if o == null or c 
     * contains o
     * @param c the set being added to
     * @param o the customer being added
     */
    private static void add(Set<Customer> c, Customer o) {
        if (o == null) {
            return; // check null object
        }
        if (c.contains(o)) {
            return; // check if collection contains the object
        }
        c.add(o); // add the object to the set
    }

    /**
     * Add an employee to the list of employees, does nothing if o == null or c
     * contains o.  This method uses binarySearch for indexing
     * @param c the list being added to
     * @param o the employee being added
     */
    private static void add(List<Employee> c, Employee o) {
        if (o == null) {
            return; // check null object
        }
        if (c.contains(o)) {
            return; // check if collection contains the object
        }
        // add the object to the proper index using binary search
        int index = Collections.binarySearch(c, o, new EmployeeComparator());
        index = index < 0 ? index * -1 - 1 : index; // fix the index for insertion
        c.add(index, o);
    }

    /**
     * Trims all strings in an array of strings
     * @param s the array of strings being trimmed
     */
    private static void trimAll(String[] s) {
        for (int i = 0; i < s.length; i++) {
            s[i] = s[i].trim();
        }
    }

    /**
     * Parses a date from a string of the format "MM/dd/yyyy"
     * @param date the string being parsed
     * @return a LocalDate of the string
     */
    private static LocalDate parseDate(String date) {
        String[] splitDate = date.split("/");
        return LocalDate.of(Integer.parseInt(splitDate[2]),
                Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]));
    }

    /**
     * Writes the set of customers and the list of employees to the specified output
     * text file.
     * @param customers the set of customers
     * @param employees the list of employees
     * @param out the output file
     */
    private static void writeTextFile(Set<Customer> customers, List<Employee> employees, File out) throws IOException{
        if (out == null) throw new IOException("The file " + out + " does not exist!"); // check for empty files
        if (out.length() <= 0) return;
        try (Formatter format = new Formatter(out)) {
            StringBuilder sb = new StringBuilder();
            for (Customer c : customers) //  output customers
            {
                sb.delete(0, sb.length()); // clear the buffer
                sb.append(c);
                format.format(sb.toString());
            }
            for (Employee e : employees) // output employees
            {
                sb.delete(0, sb.length());
                sb.append(e);
                format.format(sb.toString());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Write the contents of the set of customers and the list of employees to a
     * file in binary format
     * @param customers the set of customers
     * @param employees the list of employees
     * @param out the output file
     */
    private static void writeObjectFile(Set<Customer> customers, List<Employee> employees, File out)
    {
        if (out == null) return; // check for empty files
        if (out.length() <= 0) return;
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(new BufferedOutputStream(fos));
            
            oos.writeObject(customers); // write the object list to object file
            
            oos.writeObject(employees);
            
            oos.flush();
            oos.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Assignment_3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Assignment_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reads the lines of a text file and interprets them as either a customer or
     * an employee and adds them to the appropriate structure
     * @param customers the set that customers are stored in
     * @param employees the list that customers are stored in
     * @param in 
     */
    private static void readTextFile(Set<Customer> customers, List<Employee> employees, File in) {
        if (in == null) return; // check for empty files
        if (in.length() <= 0) return;
        try (Scanner scanner = new Scanner(in)) {
            while (scanner.hasNextLine()) // start processing file
            {
                String[] fields = scanner.nextLine().split(","); // chop the line up by fields
                trimAll(fields); // trim whitespace from fields

                // check the first field.
                if (fields[0].toLowerCase().equals("employee")) // process employees
                {
                    Employee temp = new Employee(new Name(fields[3], fields[2]),
                            parseDate(fields[4]),
                            Integer.parseInt(fields[1]),
                            parseDate(fields[5]));
                    // add the entree to the employee list
                    add(employees, temp);
                } else if (fields[0].toLowerCase().equals("customer")) // process customers
                {
                    Customer temp = new Customer(Integer.parseInt(fields[1]),
                            parseDate(fields[4]),
                            new Name(fields[3], fields[2]),
                            parseDate(fields[5]));
                    // add entry to customer set
                    add(customers, temp);
                }

            } // end processing file
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Collections.sort(employees, new EmployeeComparator());
    }
    
    /**
     * Stores the set and list within an obj file into the customers set and 
     * employees list respectively
     * @param customers the set holding the customers
     * @param employees the list holding the employees
     * @param in the file being read from
     */
    private static void readObjectFile(Set<Customer> customers, List<Employee> employees, File in)
    {
        if (in == null) return; // check for empty files
        if (in.length() <= 0) return;
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        
        try
        {
            // Setup the input stream
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            // Read the file
            customers = (Set<Customer>) ois.readObject();
            employees = (List<Employee>) ois.readObject();
            // close the stream
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        Collections.sort(employees, new EmployeeComparator());
    }
    
    /**
     * Outputs the entries of the Set of Customers and the List of employees to
     * the standard output
     * @param customers the set of customers
     * @param employees the list of employees
     */
    private static void outputStandard(Set<Customer> customers, List<Employee> employees)
    {
        for (Customer c : customers) // output customers
            System.out.print(c);
        System.out.println(""); // print a seperator
        System.out.println("*--------------------------------------------*");
        System.out.println("");
        for (Employee e : employees) // output employees
            System.out.print(e);
        System.out.println(""); // another seperator
        System.out.println("*--------------------------------------------*");
        System.out.println("");
    }
    
    /**
     * Creates an input dialog for the input file, filtering for csv files
     * @return the input file
     */
    private static File fileSelectInput()
    {
        File file = null;
        JFileChooser fileChooser = new JFileChooser(); 
        String [] extension = {"csv"};
        fileChooser.setFileFilter(new FileNameExtensionFilter(null, extension));
        int returnValue = fileChooser.showDialog(null, "Select Input File"); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file;
    }
    
    /**
     * Creates an input dialog for the object file to be used as output
     * @return the output file
     */
    private static File fileSelectObjectOutput()
    {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        String [] extension = {"obj"};
        fileChooser.setFileFilter(new FileNameExtensionFilter(null, extension));
        int returnValue = fileChooser.showDialog(null,"Select Object Output File"); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file; 
    }
    
    /**
     * Creates an input dialog for the text file to be used as output
     * @return the file being output to
     */
    private static File fileSelectTextOutput()
    {
        File file = null;
        JFileChooser fileChooser = new JFileChooser(); 
        String [] extension = {"txt"};
        fileChooser.setFileFilter(new FileNameExtensionFilter(null, extension));
        int returnValue = fileChooser.showDialog(null,"Select Text Output File"); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file; 
    }
}
