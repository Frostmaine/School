`package servlets;

import common.Account;
import common.Address;
import common.SQLReport;
import common.Customer;
import common.Money;
import common.Name;
import common.User;
import database.Database;
import database.DatabaseMySQLImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.WebErrorLogger;

/**
 * <code>BankingControl</code> is the main servlet that processes most
 * navigation requests. This servlet will redirect to other servlets depending
 * on the attributes passed and page directed from. UPDATE: LoginServlet now
 * handles all login processing. ControlServlet is now exclusively for
 * redirection.
 *
 * @author Joseph Picataggio
 */
@WebServlet(name = "BankingControl", urlPatterns = {"/BankingControl"})
public class BankingControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);//Create a new session if one does not exists
        final Object lock = session.getId().intern();//To synchronize the session variable
        String action = request.getParameter("action");
        log("BankingControl servlet called -- action is " + action);
        if (action == null) {
            //write code to forward to index.html and return
            request.getServletContext()
                    .getRequestDispatcher("/index.html")
                    .forward(request, response);
            return;
        }

        action = action.trim();
        if (action.equalsIgnoreCase("Menu")) {

            // get data from the form
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            User user = new User(lastName, firstName);

            // Make the user persistant
            session.setAttribute("user", user);

            log("Request being forwarded to Menu.jsp");
            // Forward to the Menu.jsp file and return.
            request.getServletContext()
                    .getRequestDispatcher("/jsp/Menu.jsp")
                    .forward(request, response);
        } else if (action.equalsIgnoreCase("New Customer")) { // move to NewCustomer.jsp
            request.getServletContext()
                    .getRequestDispatcher("/jsp/NewCustomer.jsp")
                    .forward(request, response);
        } else if (action.equalsIgnoreCase("add account")) {
            
            // forward back to main menu
            request.getServletContext()
                    .getRequestDispatcher("/jsp/Menu.jsp")
                    .forward(request, response);
            
        } else if (action.equalsIgnoreCase("Add Customer")) // add a new customer to the database
        {
            // Grab the data from the form
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String street = request.getParameter("street_addr");
            String city = request.getParameter("city_addr");
            String state = request.getParameter("state_addr");
            String zip = request.getParameter("zip_addr");

            LocalDate customerSince = LocalDate.now();
            LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));

            // Add to the Database
            Database db = new DatabaseMySQLImpl();
            db.insertCustomer(new Customer(-1, customerSince, new Name(lastName, firstName),
                    new Address(street, city, state, zip), birthday));

            // Log
            log("New Customer Added with name " + firstName + " " + lastName);

            // forward back to main menu
            request.getServletContext()
                    .getRequestDispatcher("/jsp/Menu.jsp")
                    .forward(request, response);

        } else if (action.equalsIgnoreCase("New Account")) { // move to newAccount.jsp

            // Grab the request data
            int cust_num = Integer.parseInt(request.getParameter("customer_num"));
            double balance = Double.parseDouble(request.getParameter("account_balance"));

            // insert into database
            Database db = new DatabaseMySQLImpl();
            if (!db.customerExists(cust_num)) // customer does not exist
            {
                log("Customer does not exist");
                 request.getServletContext()
                    .getRequestDispatcher("/jsp/NewAccount.jsp")
                    .forward(request, response);
            }
            else // customer exists
            {
            db.newAccount(new Account(-1, cust_num, new Money(balance)));

            // log
            log("New Account Added with amount " + new Money(balance));

            request.getServletContext()
                    .getRequestDispatcher("/jsp/NewAccount.jsp")
                    .forward(request, response);
            }
        } else if (action.equalsIgnoreCase("debit")) {
            // Grab the request information
            double balance = Double.parseDouble(request.getParameter("account_balance"));
            int accountNum = Integer.parseInt(request.getParameter("account_num"));

            // check the validity of values
            Database db = new DatabaseMySQLImpl();
            if (db.validateDebit(balance, accountNum)) {
                db.debit(accountNum, new Money(balance));
                log("Account " + accountNum + " debited " + new Money(balance));
                request.getServletContext()
                        .getRequestDispatcher("/jsp/Menu.jsp")
                        .forward(request, response);
            } else {
                log("Invalid Debit no operation performed");
                request.getServletContext()
                        .getRequestDispatcher("/jsp/AccountTransaction.jsp")
                        .forward(request, response);
            }

        } else if  (action.equalsIgnoreCase("credit")) {
            // Grab the request information
            double amount = Double.parseDouble(request.getParameter("account_balance"));
            int accountNum = Integer.parseInt(request.getParameter("account_num"));
            
            // check if valid account and valid amount to add
            Database db = new DatabaseMySQLImpl();
            if (db.checkAccount(accountNum) && amount >= 0)
            {
                db.credit(accountNum, new Money(amount));
                log("Account " + accountNum + " credited " + new Money(amount));
                request.getServletContext()
                        .getRequestDispatcher("/jsp/Menu.jsp")
                        .forward(request, response);
            }
            else // invalid operation
            {
                log("Invalid Credit Operation");
                request.getServletContext()
                        .getRequestDispatcher("/jsp/AccountTransaction.jsp")
                        .forward(request, response);
            }

        } else if (action.equalsIgnoreCase("Get Balance")) {
            // Get the data from the request
            int accountNum = Integer.parseInt(request.getParameter("account_num"));
            
            // put the result into a javabean to be passed to the next jsp
            Database db = new DatabaseMySQLImpl();
            SQLReport balance = new SQLReport();
            balance.setMessage(db.accountReport(accountNum));

            request.setAttribute("message", balance);
            // forward to Get Balance
            request.getServletContext()
                    .getRequestDispatcher("/jsp/GetBalance.jsp")
                    .forward(request, response);

        } else if (action.equalsIgnoreCase("List All Accounts")) {
            // Get the report form the db
            Database db = new DatabaseMySQLImpl();
            SQLReport allAccounts = new SQLReport();
            
            // Get the report from the database
            allAccounts.setMessage(db.reportAllAccounts());
            request.setAttribute("message", allAccounts);
            
            request.getServletContext()
                    .getRequestDispatcher("/jsp/ListAllAccounts.jsp")
                    .forward(request, response);
        } else if (action.equalsIgnoreCase("Account Transaction")) { // move to Transaction.jsp
            request.getServletContext()
                    .getRequestDispatcher("/jsp/AccountTransaction.jsp")
                    .forward(request, response);
        } else if (action.equalsIgnoreCase("Logout")) // remove user from session, return to index.html
        {
            session.removeAttribute("user"); // remove user from session
            request.getServletContext()
                    .getRequestDispatcher("/index.html")
                    .forward(request, response);
        } else {
            // Log an invalid action command message 
            log("Invalid Action -- index.html Returned");
            //forward to index.htm and return
            request.getServletContext()
                    .getRequestDispatcher("/index.html")
                    .forward(request, response);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ControlServlet is the main Control object for this web application";
    }// </editor-fold>
}
