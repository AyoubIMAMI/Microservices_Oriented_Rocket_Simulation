package fr.teama.rockethardwareservice.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerHelper.class);
    private static final String SERVICE_NAME = "rocket-hardware-mock";

    public static void logInfo(String logging) {
        LOGGER.info("\u001B[34m" + SERVICE_NAME + ": \u001B[32m" + logging + "\u001B[0m");
    }

    public static void logWarn(String logging) {
        LOGGER.warn("\u001B[34m" + SERVICE_NAME + ": \u001B[33m" + logging + "\u001B[0m");
    }

    public static void logError(String logging) {
        LOGGER.error("\u001B[34m" + SERVICE_NAME + ": \u001B[31m" + logging + "\u001B[0m");
    }
}
