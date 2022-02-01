package utilities;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cjones
 */
public class ErrorLogger {
    private static final String errorFileName="CS3_Error_Logger";
    public static boolean showLogInErrorWindow = true; 
    
    private static Logger errorLogger;
     /**
     * Creates a new log file name, creates a new logger object, and
     * initializes a new FileHandler to handle the logger.  The Logger
     *  object is saved in the static Logger object errorLogger as long 
     * as the property file specified is valid.
     * 
     * @throws IOException If the property file specified does 
     *                     not exists or can not be read. 
     *
     */
    
    private static void initializeLogging() throws  IOException  {
            String logFile = getLogfileName();
            errorLogger = Logger.getLogger(logFile);
            Handler handler = new FileHandler(logFile);
            handler.setFormatter(new java.util.logging.SimpleFormatter());
            if(showLogInErrorWindow) errorLogger.setUseParentHandlers(true);
            else errorLogger.setUseParentHandlers(false);
            errorLogger.addHandler(handler);
    }

    /**
     * Takes the given date and formats it into an easily readable String
     * representing this date. 
     * 
     * Date format: MM-dd-yyyy
     *
     * @param date The date we want to format.
     * @return An easily read, String representation of the given date.
     */
    private static String getFormattedDate(Date date) {
        DateFormat format;
        format = new SimpleDateFormat ("MM-dd-yyyy");

        return (format.format (date));
    }

    /**
     * Takes the given date, extracts the time out of it and creates a String
     * representation of this time that is easily read. 
     * 
     * Time format: HH.MM.AM/PM
     *
     * @param date The date from which we want to extract the time from.
     * @return A string representation of the time extracted from the given date
     * object.
     */
    private static String getFormattedTime(Date date) {
        DateFormat format;
        format = new SimpleDateFormat ("hh.mm.a");

        return (format.format (date));
    }

    /**
     * Uses the other utility methods in this class to create a date/time
     * string that is formatted in a way that is convenient for naming the
     * Weather Error file.
     * 
     * @param date The date we want to create string for.
     * @return A string that represents the data and time contained in the given
     * date.
     */
    private static String getDateTime(Date date) {
        String sDateTime = getFormattedDate(date) + "_" +
                           getFormattedTime(date);
        return sDateTime;
    }

    /**
     * Uses the current system time to create a log file name 
     * for logging errors. 
     *
     * @return A string log file name that will be unique to the minute that
     * this method was called.
     */
    private static String getLogfileName()    {
        
        String logFileName ;
        if(PropertyManager.getProperty("LogFileName")==null)
            logFileName = errorFileName;
        else
            logFileName = PropertyManager.getProperty("LogFileName"); //If property file has a file name
        //Now add day and time to minute accuracy    
        Date date = new Date(System.currentTimeMillis());
        String sFormattedDateTime = getDateTime(date);
        logFileName += "_" + sFormattedDateTime;
        if(PropertyManager.getProperty("LogFileExt")==null)  logFileName += ".log";
        else logFileName +=PropertyManager.getProperty("LogFileExt");
        return logFileName;
    }

    /**
     * Uses the given log file base name and extension and the current system
     * time to create a log file name used for creating a logger object for
     * logging exceptions. 
     * 
     * 
     * @param logFileBase Base name for the log file.
     * @param logFileExt Extension for the log file.
     * @return A string log file name that will be unique to the minute that
     * this method was called.
     */
    public static String getNewLogFileName(String logFileBase, String logFileExt) {
        String logFile = logFileBase;
        Date date = new Date(System.currentTimeMillis());
        String sFormattedDateTime = getDateTime(date);
        logFile += "_" + sFormattedDateTime;
        logFile += logFileExt;
        return logFile;
    }

    /**
     * Log message and exception with timestamp at the given standard log level.
     *
     * @param level The Level of the message.
     * @param message The message to log with the exception.
     * @param ex The exception that is being logged.
     */
    public static void log(Level level, String message, Throwable ex) {
        if(errorLogger == null) initLogger();
        errorLogger.log(level, message, ex);
    }

    /**
     * Log message with timestamp at the given standard log level.
     *
     * @param level The Level of the log message.
     * @param message The message to log.
     */
    public static void log(Level level, String message){
        if(errorLogger == null){
            initLogger();
        }
        errorLogger.log(level, message);
    }

    /**
     * Initializes logging. If initialization fails, a default logger is
     * returned that will log messages to console output.
     */
    private static void initLogger() {
        try{
            initializeLogging();
        }catch(IOException ex)  {
            errorLogger = Logger.getLogger(getLogfileName());//Will not write to a file
            errorLogger.log(Level.SEVERE, "Could not create a file handler for teh error logger");
        }
    }
    
    public static void main(String [] args){
        ErrorLogger.log(Level.SEVERE, "Test error Message");
    }

}
