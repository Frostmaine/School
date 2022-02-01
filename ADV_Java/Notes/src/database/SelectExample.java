
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import utilities.ErrorLogger;

/**
 *
 * @author cjones
 */
public class SelectExample {
    
    public static void main(String [] args){
        //Case matters 
           String SQL = "SELECT authorID, firstName, lastName   FROM authors   ORDER BY lastName ASC";
            MySQL mySQL = new MySQL();
            //Try with resource statement 
            try(Connection connection = mySQL.getConnection(); 
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SQL)){  //all 3 objects will be closed
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                String [] columnLabels = new String[columnCount+1]; // 1 to n
                for(int i = 1; i<= columnCount; i++){
                    columnLabels[i] = metaData.getColumnLabel(i);
                    System.out.printf("%-8s\t",columnLabels[i] );
                }
                System.out.println();
                while (rs.next()){
                    for(int i = 1; i<=columnCount; i++){
                       System.out.printf("%-8s\t",rs.getString(columnLabels[i]) ); 
                       //could have been rs.getString(i)  -- column number
                    }
                    System.out.println();
                }
            } catch (SQLException ex) {
               ErrorLogger.log(Level.SEVERE, null, ex);
            }
  
    }
}
