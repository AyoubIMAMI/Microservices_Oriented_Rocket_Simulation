package fr.teama.weatherservice.interfaces;

public interface ILoggerComponent {
    void logInfo(String logging);
    void logWarn(String logging);
    void logError(String logging);
}
