package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class PreparedStatementExample {
    public static void displayResultSet(ResultSet rs){
        ResultSetMetaData metaData;
        try {
            metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String [] columnLabels = new String[columnCount+1]; // 1 to n
            for(int i = 1; i<= columnCount; i++){
                columnLabels[i] = metaData.getColumnLabel(i);
                System.out.printf("%-10s\t",columnLabels[i] );
            }
            System.out.println();
            while (rs.next()){
                for(int i = 1; i<=columnCount; i++){
                System.out.printf("%-10s\t",rs.getString(columnLabels[i]) ); 
                       //could have been rs.getString(i)  -- column number
            }
            System.out.println();
        }
        } catch (SQLException ex) {
           ErrorLogger.log(Level.SEVERE, null, ex);
        }
    }
    
    public static void displayTitlesTable(){
            String selectSQL = "SELECT isbn, copyright, title   FROM titles   ORDER BY title ASC";
            MySQL mySQL = new MySQL();
            //Try with resource statement 
            try(Connection connection = mySQL.getConnection(); 
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectSQL)){  //all 3 objects will be closed
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                String [] columnLabels = new String[columnCount+1]; // 1 to n
                for(int i = 1; i<= columnCount; i++){
                    columnLabels[i] = metaData.getColumnLabel(i);
                    System.out.printf("%-10s\t",columnLabels[i] );
                }
                System.out.println();
                while (rs.next()){
                    for(int i = 1; i<=columnCount; i++){
                       System.out.printf("%-10s\t",rs.getString(columnLabels[i]) ); 
                       //could have been rs.getString(i)  -- column number
                    }
                    System.out.println();
                }
            } catch (SQLException ex) {
               ErrorLogger.log(Level.SEVERE, null, ex);
            }
    }
    
    public static void main(String [] args){
        System.out.println("Testing");
        displayTitlesTable();
        String insertSQL = "INSERT INTO titles (isbn,title,editionNumber,copyright)" +
                            "values (?, ?, ?, ?)";
        String selectSQL = "SELECT isbn, firstName, lastName FROM authors, authorISBN "+
                " where lastName = ? and authors.authorID = authorISBN.authorID ORDER BY lastName ASC";
        MySQL mySQL = new MySQL();
        
        try( Connection conn = mySQL.getConnection();
             PreparedStatement p1 = conn.prepareStatement(selectSQL);
             PreparedStatement p2 = conn.prepareStatement(insertSQL);
             Statement s1 = conn.createStatement();
            )   {
            p2.setString(1, "0137081609");
            p2.setString(2, "CORE Java: Volume II");
            p2.setInt(3, 9);
            p2.setString(4, "2013");
            p2.executeUpdate();
            p2.setString(1, "0137081898");
            p2.setString(2, "CORE Java: Volume I");
            p2.setInt(3, 9);
            p2.setString(4, "2014");
            p2.executeUpdate();
            System.out.println();
            displayTitlesTable();
            
            
            s1.executeUpdate( "INSERT INTO authors (firstName, lastName) VALUES  ('Cay','Horstmann')",
                                 Statement.RETURN_GENERATED_KEYS );
            ResultSet rs1 = s1.getGeneratedKeys();
            int id1=0;
            if(rs1.next()){
                id1=rs1.getInt(1);
                System.out.println("Authorid for Horstmann is "+id1);
            }
            
            
            s1.executeUpdate( "INSERT INTO authors (firstName, lastName) VALUES  ('Gary','Cornell')" ,
                                 Statement.RETURN_GENERATED_KEYS ); 
            rs1 = s1.getGeneratedKeys();
            int id2=0;
            if(rs1.next()){
                id2=rs1.getInt(1);
                System.out.println("Authorid for Cornell is "+id2);
            }
            s1.executeUpdate("INSERT INTO authorISBN (authorID,isbn) VALUES  ("+id1+",'0137081609')");
            s1.executeUpdate("INSERT INTO authorISBN (authorID,isbn) VALUES  ("+id1+",'0137081898')");
            s1.executeUpdate("INSERT INTO authorISBN (authorID,isbn) VALUES  ("+id2+",'0137081609')");
            s1.executeUpdate("INSERT INTO authorISBN (authorID,isbn) VALUES  ("+id2+",'0137081898')");
            p1.setString(1, "Horstmann");
            ResultSet rs = p1.executeQuery();
            System.out.println();
            displayResultSet( rs);
            
        }catch (SQLException ex){
            ex.printStackTrace();
        }

            
    }
}

