
package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class FileUtilities {
    /**
     * Returns the <code>File</code> selected by the user or null if the user
     * selects the cancel option instead of selecting a file. 
     * 
     * @return The <code>File</code> selected by the user or null. 
     */
    public static File selectFile() {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file "); // optional setting
        // int returnValue = fileChooser.showOpenDialog(null); 
       // The JFileChooser below will have no parent window and show the message 
       // "Select File" instead of "Open"
        int returnValue = fileChooser.showDialog(null,"Select File"); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file; 
    }          
    
    /**
     * Returns the <code>File</code> selected by the user to be opened or null if the user
     * selects the cancel option instead of selecting a file. 
     * 
     * @return The <code>File</code> selected by the user or null. 
     */
    public static File selectFileToOpen() {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the file to open");
        int returnValue = fileChooser.showOpenDialog(null); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file; 
    }  
   /**
     * Returns the <code>File</code> selected by the user to be opened or null if the user
     * selects the cancel option instead of selecting a file. 
     * 
     * @param title The title for the open file dialog window. 
     * @return The <code>File</code> selected by the user or null. 
     */
    public static File selectFileToOpen(String title) {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        int returnValue = fileChooser.showOpenDialog(null); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file; 
    }
    
        /**
     * Returns the <code>File</code> selected by the user to be saved or null if the user
     * selects the cancel option instead of selecting a file. 
     * 
     * @return The <code>File</code> selected by the user or null. 
     */
    public static File selectFileToSave() {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file; 
    }   
    
    /**
     * Returns the <code>Path</code> to the directory selected by the user or null if the user
     * selects the cancel option instead of selecting a directory. 
     * 
     * @return The <code>Path</code> to the directory selected by the user or null. 
     */ 
    public static Path selectDirectory(){
        Path  directoryPath = null;
        JFileChooser directoryChooser = new JFileChooser();
        directoryChooser.setDialogTitle("Select a Directory");
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//      int returnValue = directoryChooser.showOpenDialog(null); // no parent window
        int returnValue = directoryChooser.showDialog(directoryChooser, "Select Directory"); // no parent window
        String path = null;
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            directoryPath = directoryChooser.getSelectedFile().toPath()   ;
        }   
        return directoryPath;
    }
    
    // returns File object or null
    public static File selectFileToOpenByPath(String filePath){
        File file = new File(filePath);
        if(file.canRead()) return file; 
        else return null;
    }
    
    // returns File object or null
    public static File selectFileToSave(String filePath){
        File file = new File(filePath); // Locate the file
        if(file.canWrite()) return file; // If we can write to the file we are good
        try {
            if (file.createNewFile())return file; // See if we can create a new file
            else return null;
        } catch (IOException ex) {
           return null;
        }
    }
    
    public static void main (String [] args){
        // First set the look and feel to match the system executing the code.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Debug.println( "Error setting the GUI look and feel.");
            Debug.println(ex.getMessage()); // show the entire error message
        }
        
        //Test the code to select a file.
        File file = selectFile();
        if(file !=null) {
            System.out.println("The file selected was "+ file.getAbsolutePath());
        } else {
            System.out.println("The user did not select a file");
        }
        
        //Test the code to open a file.
        file = FileUtilities.selectFileToOpen();
        if(file !=null) {
            System.out.println("The file selected to be opened was "+ file.getAbsolutePath());
        } else {
            System.out.println("The user did not select a file");
        }
       
        //Test the code to save a file.
        file = FileUtilities.selectFileToSave();
        if(file !=null) {
            System.out.println("The file selected to be saved was "+ file.getAbsolutePath());
            if (file.exists()){
                System.out.println("This file does exist. ");
            } else {
                System.out.println("This file does not exist. ");
            }
        } else {
            System.out.println("The user did not select a file");
        }
        
        //Test the code to obtain a directory
        Path  path = selectDirectory();
        if (Files.exists(path)) { // if path exists, output info about it
         // display directory  information -- same would work for files as well
      	System.out.printf("%n%s exists%n", path.getFileName());
      	System.out.printf("%s a directory%n", 
      		Files.isDirectory(path) ? "Is" : "Is not");
      	System.out.printf("%s an absolute path%n", 
      		path.isAbsolute() ? "Is" : "Is not");
        
        try {
                System.out.printf("Last modified: %s%n",
                        Files.getLastModifiedTime(path));
                System.out.printf("Size: %s%n", Files.size(path));
            } catch (IOException ex) {
                Debug.println( "IO Exception \n" + ex);
        }

      	System.out.printf("Path: %s%n", path);
      	System.out.printf("Absolute path: %s%n", path.toAbsolutePath());

         if (Files.isDirectory(path)){  // output directory listing
            System.out.printf("%nDirectory contents:%n");
            
            // object for iterating through a directory's contents
            DirectoryStream<Path> directoryStream;
            try {
                  directoryStream = Files.newDirectoryStream(path);
   
                  for (Path p : directoryStream)
                       System.out.println(p);
                } catch (IOException ex) {
                    Debug.println( "IO Exception \n" + ex);
           }
         } 
      } 
      else {// not a valid path
         System.out.printf("%s does not exist%n", path);
      }  
        
    }// End of main
    
}

