package database;

import common.DatabaseProperty;
import common.ErrorLog;
import common.User;
import common.UserRole;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import utilities.WebErrorLogger;

/**
 * A utility class designed to convert result set entries to  
 * Java objects. 
 * 
 * @author @author Curt Jones (2018)
 */
public class SQLUtility {

    public static User convertResultSetToUser(ResultSet rs) {
        User user = new User();
        try {
            user.setUserNumber(rs.getInt("userNumber"));
            user.setUserPassword(rs.getString("userPassword"));
            user.setLoginName(rs.getString("loginName"));
            user.setEmailAddress(rs.getString("emailAddress"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setUserRole(UserRole.getUserRole(rs.getString("userRole")));
            LocalDateTime now = LocalDateTime.parse(rs.getString("lastLoginTime"));
            user.setLastLoginTime(now);
            if (rs.getString("LastAttemptedLoginTime") == null) {
                user.setLastAttemptedLoginTime(LocalDateTime.now());
            } else {
                user.setLastAttemptedLoginTime(LocalDateTime.parse(rs.getString("LastAttemptedLoginTime")));
            }
            int loginCount = rs.getInt("loginCount");
            user.setLoginCount(loginCount);
            user.setAttemptedLoginCount(rs.getInt("attemptedLoginCount"));
            user.setLocked(rs.getBoolean("locked"));
            user.setSalt(rs.getString("salt"));

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in convertResultSetToUser()" + ex, ex);
            return null;
        }
        return user;
    }
    
    public static DatabaseProperty convertResultSetToDatabaseProperty(ResultSet rs) {
        DatabaseProperty property = new DatabaseProperty();
        try {
            property.setPropertyNumber(rs.getInt("propertyNumber"));
            property.setPropertyName(rs.getString("propertyName"));
            property.setPropertyValue(rs.getString("propertyValue"));
            property.setDescription(rs.getString("description"));
            property.setPreviousValue(rs.getString("previousValue"));
            property.setDefaultValue(rs.getString("defaultValue"));
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in convertResultSetToDatabaseProperty. " + ex, ex);
            return null;
        }
        return property;
    }

    public static ErrorLog convertResultSetToErrorLog(ResultSet rs) {
         ErrorLog errorLog = new ErrorLog();
         try {
            errorLog.setErrorLogID(rs.getInt("EVENT_ID"));
            String date = rs.getString("EVENT_DATE");
            LocalDateTime time = LocalDateTime.parse(date);
            errorLog.setErrorLogDateTime(time);
            errorLog.setErrLevel(rs.getString("LEVEL"));
            errorLog.setLoggerName(rs.getString("LOGGER"));
            errorLog.setErrorMessage(rs.getString("MSG"));
            errorLog.setException(rs.getString("THROWABLE"));
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in convertResultSetToErrorLog(ResultSet rs). " + ex, ex);
            return null;
        }
         
         return errorLog;
    }
}
