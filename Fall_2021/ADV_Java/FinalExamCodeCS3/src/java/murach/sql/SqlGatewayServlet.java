package murach.sql;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class SqlGatewayServlet extends HttpServlet {
   // One way to get parameters information into the code -- use symbolic constants
    private static final String mysqlPrefix="jdbc:mysql://";
    private static final String hostname ="classdb.mads.bloomu.edu";
    private static final String databaseName = "Compsci221"; 
    private static final String databaseURL = mysqlPrefix+hostname +"/"+databaseName;
    private static final String userName = "csuser"; 
    private static final String password = "csuser"; 
    
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action == null) {
        
        String sqlStatement = request.getParameter("sqlStatement");
        String sqlResult = "";
        try {
            // load the driver
           Class.forName("com.mysql.cj.jdbc.Driver");
            
            /* get a connection -- code from textbook */
            String databaseURL = "jdbc:mysql://classdb.mads.bloomu.edu:3306/mty87392?autoReconnect=true";
            String userName = "mty87392";
            String password = "mty87392";
            
            //Using our code from statements 11 to 16 above -- needs to be a local
            //variable. Threading issue otherwise 
            Connection connection = DriverManager.getConnection(
                    databaseURL, userName, password);

            // create a statement
            Statement statement = connection.createStatement();

            // parse the SQL string
            sqlStatement = sqlStatement.trim();
            if (sqlStatement.length() >= 6) {
                String sqlType = sqlStatement.substring(0, 6);
                if (sqlType.equalsIgnoreCase("select")) {
                    // create the HTML for the result set
                    ResultSet resultSet
                            = statement.executeQuery(sqlStatement);
                    sqlResult = SQLUtil.getHtmlTable(resultSet);
                    resultSet.close();
                } else {
                    int i = statement.executeUpdate(sqlStatement);
                    if (i == 0) { // a DDL statement
                        sqlResult = 
                                "<p>The statement executed successfully.</p>";
                    } else { // an INSERT, UPDATE, or DELETE statement
                        sqlResult = 
                                "<p>The statement executed successfully.<br>"
                                + i + " row(s) affected.</p>";
                    }
                }
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            sqlResult = "<p>Error loading the databse driver: <br>"
                    + e.getMessage() + "</p>";
        } catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
        }

        request.setAttribute("sqlResult", sqlResult);
        request.setAttribute("sqlStatement", sqlStatement);
        }
        else if (action.equalsIgnoreCase("sqlGatewayLogin")) {
            String first_name = request.getParameter("first_name").toString();
            String last_name = request.getParameter("last_name").toString();
            String birthday = request.getParameter("birthday").toString();
            
            session.setAttribute("firstName", first_name);
            session.setAttribute("lastName", last_name);
            session.setAttribute("birthday", birthday);
             String url = "/index.jsp";
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
        }
        String url = "/index.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}