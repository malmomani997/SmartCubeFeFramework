package Base.Resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    private final Logger logger;

    // ANSI escape codes for colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";

    // Constructor that takes the class to log
    public Log(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    // Custom info log with green color
    public void info(String message, Object... args) {
        logger.info(ANSI_GREEN + "{}" + ANSI_RESET, format(message, args));
    }

    // Custom error log with red color
    public void error(String message, Object... args) {
        logger.error(ANSI_RED + "{}" + ANSI_RESET, format(message, args));
    }

    // Custom warn log with yellow color
    public void warn(String message, Object... args) {
        logger.warn(ANSI_YELLOW + "{}" + ANSI_RESET, format(message, args));
    }

    // Custom debug log with cyan color
    public void debug(String message, Object... args) {
        logger.debug(ANSI_CYAN + "{}" + ANSI_RESET, format(message, args));
    }

    // Utility method to format messages with placeholders and arguments
    private String format(String message, Object... args) {
        for (Object arg : args) {
            // Escape special characters in the argument
            String escapedArg = escapeSpecialCharacters(arg.toString());
            message = message.replaceFirst("\\{}", escapedArg);
        }
        return message;
    }

    // Escape special characters that could interfere with replaceFirst
    private String escapeSpecialCharacters(String input) {
        return input.replaceAll("([\\\\$&+,:;=?@#|'<>.^*()%!\\[\\]])", "\\\\$1");
    }
}
