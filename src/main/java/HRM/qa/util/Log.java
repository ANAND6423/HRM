package HRM.qa.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Log {

    private static final Logger logger = LogManager.getLogger(Log.class);

    private Log() {
        // Prevent instantiation
    }

    private static String buildMessage(String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // Index 0 = getStackTrace, 1 = buildMessage, 2 = log method, 3 = caller
        StackTraceElement caller = stackTrace[3];
        return String.format("[%s.%s():%d] %s",
                caller.getClassName(),
                caller.getMethodName(),
                caller.getLineNumber(),
                message);
    }

    public static void info(String message) {
        logger.info(buildMessage(message));
    }

    public static void debug(String message) {
        logger.debug(buildMessage(message));
    }

    public static void warn(String message) {
        logger.warn(buildMessage(message));
    }

    public static void error(String message) {
        logger.error(buildMessage(message));
    }

    public static void error(String message, Throwable t) {
        logger.error(buildMessage(message), t);
    }
}
