package utilities;

import jakarta.mail.Address;
import java.util.Properties;
import java.util.logging.Level;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 *
 * @author Curt Jones (2019)
 */
public class GmailUtility {
    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML) {
        boolean debugMode = false;
        try {
            // 1 - get a mail session
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.port", 465);
            props.put("mail.smtps.auth", "true");
            props.put("mail.smtps.quitwait", "false");
            Session session = Session.getDefaultInstance(props);
            session.setDebug(debugMode);
            
            // 2 - create a message
            Message message = new MimeMessage(session);
            message.setSubject(subject);
            if (bodyIsHTML) {
                message.setContent(body, "text/html");
            } else {
                message.setText(body);
            }
            
            // 3 - address the message
            Address fromAddress = new InternetAddress(from);
            Address toAddress = new InternetAddress(to);
            message.setFrom(fromAddress);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            
            try ( // 4 - send the message
                    Transport transport = session.getTransport()) {
                String gmailUser = getUser();
                String gmailPassword = getPassword();
                transport.connect(gmailUser, gmailPassword);
                transport.sendMessage(message, message.getAllRecipients());
            }
        } catch (MessagingException ex) {
            WebErrorLogger.log(Level.WARNING, "Could not send mail using GmailUtility ", ex);
        }
    }

    private static String getUser() {
        String user = PropertyManager.getProperty("gmailAccount");
        if (user == null){ 
           WebErrorLogger.log(Level.WARNING, "gmailAccount property was null");
           user = "BUWeatherProject@gmail.com";
        }
        return user;
    }

    private static String getPassword() {
        String password = PropertyManager.getProperty("gmailAccountPassword");
        if (password == null){ 
           WebErrorLogger.log(Level.WARNING, "gmailAccountPassword property was null");
           password = "SoftwareEngineering2014";
        }
        return password;
    }
    
    public static void main(String [] args){
        utilities.Debug.setEnabled(true);
        final String propertyFilePath = common.Paths.HOMEDIRECTORY+"/web/WEB-INF/config/General.properties";
        PropertyManager.configure(propertyFilePath);
        String to = "cjones@bloomu.edu";
        String from  ="BUWeatherProject";
        String subject = "Testing of GmailUtility";
        String body= "This is a test of our Gmail Utility class";
        boolean bodyIsHTML = false;
        sendMail(to, from, subject, body, bodyIsHTML);
    }


}
