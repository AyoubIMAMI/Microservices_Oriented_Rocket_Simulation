package fr.teama.logsservice.interfaces;

import fr.teama.logsservice.models.MissionLog;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ILogsManager {
    void saveLog(String serviceName, String text, LocalDateTime date);

    List<MissionLog> getAllLogs();

    List<MissionLog> getAllLogs(String rocketName);

    void resetDb();

    ResponseEntity<String> changeRocketName(String rocketName);
}
