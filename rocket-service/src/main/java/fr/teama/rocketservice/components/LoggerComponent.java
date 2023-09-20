package fr.teama.rocketservice.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerComponent {
    public static final Logger LOG = LoggerFactory.getLogger(LoggerComponent.class);

    public void logInfo(String logging) {
        LOG.info("\u001B[32m" + logging + "\u001B[0m");
    }

    public void logWarn(String logging) {
        LOG.warn("\u001B[33m" + logging + "\u001B[0m");
    }

    public void logError(String logging) {
        LOG.error("\u001B[31m" + logging + "\u001B[0m");
    }
}
