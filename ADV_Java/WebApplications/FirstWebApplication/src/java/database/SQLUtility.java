package database;

import common.DatabaseProperty;
import common.ErrorLog;
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
