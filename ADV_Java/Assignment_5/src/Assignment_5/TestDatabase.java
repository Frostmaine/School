package Assignment_5;

import java.sql.SQLException;

/**
 *
 * @author frostmaine
 */
public class TestDatabase {

    public static void main(String[] args) throws SQLException {
        
        // Test from the bottom up, more easily determining if errors are coming
        // from help methods and such
        DatabaseMySQLImpl db = new DatabaseMySQLImpl(); // the database object
        
        
        
        
        db.getConnection().commit();
        db.getConnection().close(); // ensure the datbase is closed
    }
    
}
