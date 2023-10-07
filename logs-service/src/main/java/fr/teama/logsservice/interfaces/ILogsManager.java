package fr.teama.logsservice.interfaces;

import fr.teama.logsservice.models.MissionLog;

import java.time.LocalDateTime;
import java.util.List;

public interface ILogsManager {
    void saveLog(String serviceName, String text, LocalDateTime date);
    List<MissionLog> getAllLogs();

    void clearLogs();
}
