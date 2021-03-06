
package Assignment_5;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import utilities.ErrorLogger;
import utilities.PropertyManager;

/**
 * This class manages connections to a database. 
 * @author cjones
 */
public class MySQL {
  
    private final String databaseURL;
    private final String userName;
    private final String password;
    private final String mysqlPrefix = "jdbc:mysql://";
    /**
     * Database connections are created using credentials provided in the property file.
     * These credentials may be encoded if the secret key file name is given.
     */        
    public  MySQL( ){
  
      String hostname = PropertyManager.getProperty("MySQLHostName").trim();
      String databaseName = PropertyManager.getProperty("DatabaseName").trim();
   //   databaseURL = mysqlPrefix + hostname + "/" + databaseName+"?serverTimezone=America/New_York";
      databaseURL = mysqlPrefix + hostname + "/" + databaseName;
      userName = PropertyManager.getProperty("MySQLUserName").trim();
      password = PropertyManager.getProperty("MySQLPassword").trim();
             initDB();
    }
    
    /**
     * Database connections are created using credentials provided in the parameter list.
     * @param databaseURL The database connection string 
     *                    (jdbc:mysql://Classdb.bloomu.edu/Compsci221).
     * @param userName The user name for this database connection. 
     * @param password The password for this database connection.
     */
    public  MySQL(String databaseURL, String userName, String password ){
        this.databaseURL = databaseURL;
        this.userName = userName;
        this.password = password;
        initDB();
    }

    /**
     * Ensures that we can connect to the database given the current connection parameters. 
     */
    private void initDB() {
        try {
           Connection conn = DriverManager.getConnection(databaseURL,userName,password);
           conn.close();
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "Could not connect to the database. "
                    + "Database string = "+
                    databaseURL+ " user =  "+userName + " password "+password, ex);
            System.exit(1);
        }
    }
    
     /**
     * Returns a connection to the database. Connections are not reused. 
     * Each connection should be closed after use. 
     * 
     * @return A <code>Connection</code> to the database.
     */
    public Connection getConnection() {
       Connection conn = null;
       /*
       try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); //Not normally needed for MySQL - but ...
        } catch (ClassNotFoundException ex) {
            ErrorLogger.log(Level.SEVERE, "Could not find the class com.mysql.jdbc.Driver \n"
                    + "Program will now exit. ", ex);
            System.exit(1);
        } catch (InstantiationException ex) {
            ErrorLogger.log(Level.SEVERE, "Could not instaniate the class com.mysql.jdbc.Driver \n"
                    + "Program will now exit. ", ex);
            System.exit(1);
        } catch (IllegalAccessException ex) {
            ErrorLogger.log(Level.SEVERE, "Could not access the class com.mysql.jdbc.Driver \n"
                    + "Program will now exit. ", ex);
            System.exit(1);
        }
*/
        try {
            conn = DriverManager.getConnection(databaseURL,userName,password);
        } catch (SQLException e) {
            ErrorLogger.log(Level.SEVERE,e.getMessage()); 
            ErrorLogger.log(Level.SEVERE, "Could not connect to the database. "
                    + "Database string = "+
                    databaseURL+ " user =  "+userName + " password "+password);
            System.exit(1);
        }
       return conn;
    }
    
    public void closeConnection(Connection connection){
        if (connection != null) { 
          try { 
              connection.close(); 
              } catch (SQLException e) {
                    ErrorLogger.log(Level.SEVERE, "SQL Exception is thrown while " +
                    "trying to close a Connection object. The connection " + 
                    "object was not null.", e); 
              }
        }
    }
    
    public static void main(String args[]){
        utilities.Debug.setEnabled(true);
        final String propertyFilePath = utilities.PATHS.HOME_DIRECTORY+"/General.properties";
        PropertyManager.configure();
        MySQL database = new MySQL();
        
    }
    
}
