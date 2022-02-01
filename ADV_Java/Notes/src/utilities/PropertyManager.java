
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Level;



/**
 * Utility class to access all properties and property files.
 *
 * 
 *
 */
public class PropertyManager {

    private static Properties properties;
    private final static String propertyFilepath = "General.properties";
   
  
    /**
     * Configures properties for this program.  
     */
    public static void configure(String propertyFilepath) {
        Debug.println("Configuring Property Manager");
        try {
            properties = new Properties();
            properties.load(new FileInputStream(
                    new File(propertyFilepath)));
        } 
        catch (FileNotFoundException ex) {
            ErrorLogger.log(Level.SEVERE,"Could not open the properties file: "+ propertyFilepath,  ex);  
        } catch (IOException ex) {
            ErrorLogger.log(Level.SEVERE,"Could not read the properties file: "+ propertyFilepath,  ex);
        } 
    }

    /**
     * Obtains the requested property from the general properties file.
     *
     * @param key the name of the property requested.
     * @return The value of the property requested or null if 
     * this key does not exist in the property file. 
     */
    public static String getProperty(String key) {
        if (properties == null) {
            configure(propertyFilepath);
        }
        return properties.getProperty(key);
    }

   /**
    * Sets the a property value for the given key and stores it in the property file 
    * for the next program execution. 
    * 
    * @param key The key for this property value. 
    * @param value The property value associated with the given key. 
    */
    public static void setProperty(String key, String value) {
        try {
            properties.setProperty(key, value);
            properties.store(new FileOutputStream(new File(propertyFilepath)), null);
        } catch (IOException ex) {
            ErrorLogger.log(Level.SEVERE, ex.getMessage());
        }
    }

   
    /**
     * Encrypts a string in hexadecimal format using the function specified in
     * the local properties file. The string will not be encrypted if the
     * encryption property in the local properties file is set to "none", is an
     * empty string, or does not exist in the file.
     *
     * @param orig The original string.
     * @return The encrypted string.
     */
    public static String encrypt(String orig) {
        String alg = getProperty("ENCRYPTION");
        if (orig == null || alg.isEmpty() || alg.equals("none") || alg.startsWith("Invalid")) {
            return orig;            // last condition is in case property does not exist; see getProperty()
        }
        byte[] bytes = null;
        try {
            // gets bytes from encryption algorithm
            bytes = MessageDigest.getInstance(alg).digest(orig.getBytes());
        } catch (NoSuchAlgorithmException e) {
            String msg = "The encryption property in "+propertyFilepath+"  is set to '"
                    + alg + "'; this algorithm is not available or does not exist.";
            ErrorLogger.log(Level.SEVERE, msg, e);
            return orig;
        }

        // translates bytes to hex string
        StringBuilder hexStrBuf = new StringBuilder();
        for (byte b : bytes) {
            String str = Integer.toHexString(b & 0xff);
            hexStrBuf.append(str.length() == 1 ? "0" : "").append(str);
        }

        return hexStrBuf.toString();
    }

    

}
