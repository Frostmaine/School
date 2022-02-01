package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.Debug;

/**
 * If configured correctly, this Servlet obtains the Shibboleth passed parameters
 * ands displays them on a web page.  Notice the URL pattern is different
 * from the Servlet name. The server must be configured correctly for this to work.
 * 
 * @author Curt Jones
 */
@WebServlet(name = "BULogin", urlPatterns = {"/BULogin"})
public class ShowShibbolethParameters extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. In this case we are just printing SSO parameters from the 
     * Bloomsburg Login
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Debug.println("/BULogin");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MFSP</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MFSP at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
       
        /* names of the SAML attributes to display */
    String[] shib_attributes = {
        "persistent-id","eppn","affiliation","unscoped-affiliation","givenName",
        "mail","displayName","sn","employeeNumber","employeeID","uid","studentID", "StudentIDNumber", "IDNumber"
        /*
        "Shib-user","Shib-displayName","Shib-surName",
        "Shib-commonName","Shib-givenName","Shib-eduPersonPN",
        "Shib-email","Shib-HomeOrg","Shib-uid",
        "Shib-userStatus","Shib-voName","Shib-memberOf", "Shib-Identity-Provider",
        "Shib-Session-ID", 
        */
    };
 
    out.println("<h2>SAML attributes</h2>");
    out.println("<table>");
    for (int i=0; i<shib_attributes.length; i++){
        out.print("<tr><td>"+shib_attributes[i]+"</td>");
        out.print("<td>"+request.getAttribute(shib_attributes[i])+"</td></tr>\n");
 //           out.print("<tr><td>"+"(Session) " + shib_attributes[i]+"</td>");
//        out.print("<td>"+request.getSession().getAttribute(shib_attributes[i])+"</td></tr>\n");
//        out.print("<tr><td>"+"(Header) " + shib_attributes[i]+"</td>");
 //       out.print("<td>"+request.getHeader(shib_attributes[i])+"</td></tr>\n");

    }
    out.println("</table>");
 
    /* also print generic attributes
     * NOTE: this will nog display the SAML attributes, because for some
     * reason these are not included in request.getAttributeNames()
     */
    out.println("<h2>Attributes:</h2>");
    out.println("<table>");
    
    Enumeration<String> attributes = request.getAttributeNames();
    while ( attributes.hasMoreElements() ) {
        String attr_name = (String) attributes.nextElement();
        Object attr_val  = request.getAttribute(attr_name);
 
        out.print("<tr><td>"+attr_name+"</td>");
        out.print("<td>"+attr_val+"</td></tr>\n");
    }
    out.println("</table>");
 
    out.println("<h2>Session Attributes:</h2>");
    out.println("<table>");
    HttpSession session =request.getSession();
    Enumeration<String> sessionAttributes = session.getAttributeNames();
    while ( sessionAttributes.hasMoreElements() ){
        String attr_name = (String) sessionAttributes.nextElement();
        Object attr_val  = request.getAttribute(attr_name);
 
        out.print("<tr><td>"+attr_name+"</td>");
        out.print("<td>"+attr_val+"</td></tr>\n");
    }
    out.println("</table>");
    

        
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
    out.println("<h2>Cookies:</h2>");
    out.println("<table>");
    for (int i=0; i<cookies.length; i++){
        String attr_name = (String) cookies[i].getName();
        Object attr_val  = (String) cookies[i].getValue();;
 
        out.print("<tr><td>"+attr_name+"</td>");
        out.print("<td>"+attr_val+"</td></tr>\n");
    }
    out.println("</table>");
    }
    
    
   out.println("<h2>Parameters:</h2>");
   out.println("<table>");
   Enumeration<String> parameterNames = request.getParameterNames();
    while ( parameterNames.hasMoreElements() ){
        String attr_name = (String) parameterNames.nextElement();
        Object attr_val  = request.getParameter(attr_name);
 
        out.print("<tr><td>"+attr_name+"</td>");
        out.print("<td>"+attr_val+"</td></tr>\n");
    }
    out.println("</table>");
    
    out.println("Authenication type is " + request.getAuthType());
    out.println("Session id is "+ session.getId());
    
    Enumeration<String> headerNames = request.getHeaderNames();
   out.println("<h2>Headers:</h2>");
   out.println("<table>");

    while ( headerNames.hasMoreElements() ){
        String attr_name = (String) headerNames.nextElement();
        Object attr_val  = request.getHeader(attr_name);
 
        out.print("<tr><td>"+attr_name+"</td>");
        out.print("<td>"+attr_val+"</td></tr>\n");
    }
    out.println("</table>");
    
    
    out.println("<h2>Finished</h2>");
 
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
        return "Short description";
    }// </editor-fold>

}
