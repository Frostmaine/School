package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*
* @author kvonbloh
*/
public class Jdbctest {

    //static String url = "jdbc:mysql://classdb.mads.bloomu.edu:3306";
    static String url = "jdbc:mysql://classdb.mads.bloomu.edu:3306";
  //  static String creds = "?user=CS3User2021&password=CS3User2021&useSSL=false&allowPublicKeyRetrieval=true";
    static String creds = "?user=mty87392&password=mty87392";
    static String tz = "";
    static String db = "mty87392";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
//          Connection con = DriverManager.getConnection(url+creds+tz);
            Connection con = DriverManager.getConnection(url+"/"+db+creds+tz);
            System.out.println("***************************************************************");
            System.out.println("********     Using " + url + "/" + db + tz +"     ********");
            System.out.println("***************************************************************");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {
            System.out.println("A file exception occurred: " + e.getMessage());
        }
    }
    
}
