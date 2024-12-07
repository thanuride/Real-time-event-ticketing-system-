import java.io.IOException;
import java.util.logging.*;

public class Log {
    private static final Logger logger = Logger.getLogger(Log.class.getName());
    private static FileHandler fileHandler;

    static {
        try {

            // Clear existing handlers to prevent logging to the console
            Handler[] handlers = logger.getHandlers();
            for (Handler handler : handlers) {
                logger.removeHandler(handler);
            }

            // Set up the file handler to write logs to a file
            fileHandler = new FileHandler("system.log", true);
            fileHandler.setFormatter(new SimpleFormatter());  // Basic text formatting
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);


        } catch (IOException e) {
            System.err.println("Error initializing logger: " + e.getMessage());
        }
    }

    // Log Info Level messages
    public static void logInfo(String message) {
        logger.info(message);
    }

    // Log Warning Level messages
    public static void logWarning(String message) {
        logger.warning(message);
    }

    // Log Error Level messages
    public static void logError(String message) {
        logger.severe(message);
    }

    // Log Exception with a message
    public static void logException(String message, Exception e) {
        logger.log(Level.SEVERE, message, e);
    }

}
