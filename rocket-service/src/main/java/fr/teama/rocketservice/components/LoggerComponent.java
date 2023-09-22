package fr.teama.rocketservice.components;

import fr.teama.rocketservice.interfaces.ILoggerComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoggerComponent implements ILoggerComponent {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerComponent.class);

    @Value("${service.name}")
    private String serviceName;

    @Override
    public void logInfo(String logging) {
        LOG.info("\u001B[34m" + serviceName + ": \u001B[32m" + logging + "\u001B[0m");
    }

    @Override
    public void logWarn(String logging) {
        LOG.warn("\u001B[34m" + serviceName + ": \u001B[33m" + logging + "\u001B[0m");
    }

    @Override
    public void logError(String logging) {
        LOG.error("\u001B[34m" + serviceName + ": \u001B[31m" + logging + "\u001B[0m");
    }
}
