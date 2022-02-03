
package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import utilities.ErrorLogger;

/**
 *
 * @author cjones
 */
public class InsertExample {
    
    
    public static void main(String [] args){
        //Case matters 
        String sql = "INSERT INTO authors (firstName, lastName) VALUES ('Sue', 'Red');";
        MySQL mySQL = new MySQL();
        //Try with resource statement 
            try(Connection connection = mySQL.getConnection(); 
                Statement statement = connection.createStatement()){
                
                statement.executeUpdate(sql);
                
                sql= "UPDATE authors    SET lastName = 'Black'   WHERE lastName = 'Red' AND firstName = 'Sue';";
                
                statement.executeUpdate(sql);
                
            } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, null, ex);
        }
                
    }
}
