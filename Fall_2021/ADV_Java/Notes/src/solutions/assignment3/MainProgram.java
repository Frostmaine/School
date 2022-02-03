package solutions.assignment3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import utilities.Debug;



/**
 *
 * @author cjones
 */
public class MainProgram {
    
   //Needed to sort customers by birthdate
    public static class  ByBirthdate implements Comparator<Customer>{

        @Override
        public int compare(Customer o1, Customer o2) {
           return o1.getBirthdate().compareTo(o2.getBirthdate());
        }
        
    }
    
    //Needed to display data structures to the user 
    public static void displayCollection(Collection collection){
       Iterator it = collection.iterator();

       while(it.hasNext()){
           System.out.print(it.next());
       }

    };
  
    public static void main(String[] args) {
         
       Collection <Employee> employees = new LinkedList();
       Collection <Customer> customers = new HashSet();
       
       // First set the look and feel to match the system executing the code.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Debug.println( "Error setting the GUI look and feel.");
        }

       //Read File
       File inputFile = utilities.FileUtilities.selectFileToOpen("Please select the input file");
       readTextFile(inputFile, employees, customers);
       
       //Write data structures to object files and text files
       File outputTextFile = utilities.FileUtilities.selectFileToOpen("Please specify the text output file"); 
       File outputObjectFile = utilities.FileUtilities.selectFileToOpen("Please specify the object output file");
       outputFiles(outputTextFile,outputObjectFile, employees, customers);
 
       //Output the original file
       System.out.println("\nAfter reading the original text file");
       System.out.println("Employees");
       displayCollection(employees); 
       System.out.println("Customers");
       displayCollection(customers); 
    
       //Clear data structures
       customers.clear();
       employees.clear();
       
       //Read back from object files
       readObjectFile(outputObjectFile, employees,  customers); // does not work -- why? 
       //This will work, why is this needed?????
       try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(outputObjectFile))) {
               employees = (LinkedList) ois.readObject();
               customers = (HashSet) ois.readObject();
               ois.close();
           } catch (IOException | ClassNotFoundException ex) {
             System.out.println("An error occured while writing the object output file. The program will terminate.");
             System.exit(1);
        }
      
       //Display structures 
       System.out.println("\nAfter reading from the object file");
       System.out.println("Employees");
       displayCollection(employees); 
       System.out.println("Customers");
       displayCollection(customers); 
     
       // Clear structures
         customers.clear();
         employees.clear();
       
        //Read from text file
         readTextFile(outputTextFile, employees, customers);
       
       // Display -- no points
       System.out.println("\nAfter reading from the text file we created");
       System.out.println("Employees");
       displayCollection(employees); 
       System.out.println("Customers");
       displayCollection(customers); 
       
       // Collections.sort customers by birthdate -- 
       Customer [] array = new Customer[0];
       array = customers.toArray(array);
       List<Customer> list = Arrays.asList(array);
       Collections.sort(list, new ByBirthdate());
       System.out.println("\nCustomers sorted by birthdate");
       displayCollection(list); 
       

    }
    
    
    private static void readTextFile(File inputFile, Collection<Employee> employees, Collection<Customer> customers) {
        if (inputFile==null){
            System.out.println("No input file was selected. The program will terminate.");
            System.exit(1);
        }
        Scanner scanner=null;
        try {
              scanner = new Scanner(inputFile.toPath());
              String inputLine;
              while (scanner.hasNextLine()){
                inputLine = scanner.nextLine();
                String[] data = inputLine.split(",");
                String[] birthDate = data[4].split("/");
                String[] date2 = data[5].split("/");
                if(data[0].equalsIgnoreCase("Customer")){
                   int customerID = Integer.parseInt(data[1].trim());
                   Name name=new Name(data[3].trim(), data[2].trim());
                   LocalDate customerSince = LocalDate.of(Integer.parseInt(date2[2]), 
                            Integer.parseInt(date2[0]), Integer.parseInt(date2[1]));
                   LocalDate birthday =  LocalDate.of(Integer.parseInt(birthDate[2]), 
                            Integer.parseInt(birthDate[0]), Integer.parseInt(birthDate[1]));
                   
                   Customer customer = new Customer();  
                   customer.setCustomerIdentificationKey(customerID);
                   customer.setCustomerSince(customerSince);
                   customer.setBirthdate(birthday);
                   customer.setName(name);

                   customers.add(customer);

                }
                else if(data[0].equalsIgnoreCase("Employee") ){
                   int employeeID = Integer.parseInt(data[1].trim());
                   Name name=new Name(data[3].trim(), data[2].trim());
                   LocalDate dateHired = LocalDate.of(Integer.parseInt(date2[2]), 
                            Integer.parseInt(date2[0]), Integer.parseInt(date2[1]));
                   LocalDate birthday =  LocalDate.of(Integer.parseInt(birthDate[2]), 
                            Integer.parseInt(birthDate[0]), Integer.parseInt(birthDate[1]));
                   
                   Employee employee = new Employee(); 
                   employee.setEmployeeIdentificationKey(employeeID);
                   employee.setName(name);
                   employee.setBirthdate(birthday);
                   employee.setDateHired(dateHired);

                   employees.add(employee);
                }
            }
        } catch (IOException ex) {
            System.out.println("An error occured while reading "+inputFile.getAbsolutePath()+". The program will terminate.");
            System.exit(1);
        } finally {
            if(scanner != null)
                scanner.close(); 
        }
        
    }
    
    
    private static void outputFiles(File outputTextFile, File outputObjectFile, Collection<Employee> employees, Collection<Customer> customers) {
       if (outputTextFile==null ||outputObjectFile==null ){
            System.out.println("One of the output files did not exist. The program will terminate.");
            System.exit(1);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputObjectFile))) {
               oos.writeObject(employees);
               oos.writeObject(customers);
           } catch (IOException ex) {
             System.out.println("An error occured while writing the object output file. The program will terminate.");
             System.exit(1);
        }
        try (PrintWriter writer = new PrintWriter (outputTextFile)){
            for(Employee e : employees){
                writer.write(e.toString());
            }
            for(Customer c : customers){
                writer.write(c.toString());
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("An error occured while writing the text output file. The program will terminate.");
            System.exit(1);
        }
    }

// This method will not correctly return the data structures, why??????    
    private static void readObjectFile(File inputObjectFile, Collection<Employee> employees, Collection<Customer> customers){
        if (inputObjectFile==null){
            System.out.println("The object input file did not exist. The program will terminate.");
            System.exit(1);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputObjectFile))) {
               employees = (LinkedList) ois.readObject();
               customers = (HashSet) ois.readObject();
               ois.close();
           } catch (IOException | ClassNotFoundException ex) {
             System.out.println("An error occured while writing the object output file. The program will terminate.");
             System.exit(1);
        }
    }
}
