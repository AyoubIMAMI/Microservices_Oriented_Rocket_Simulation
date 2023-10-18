package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.models.SavedMissionLog;
import fr.teama.missionservice.exceptions.LogsServiceUnavailableException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ILogsProxy {
    ResponseEntity<List<SavedMissionLog>> getAllLogs() throws LogsServiceUnavailableException;

    ResponseEntity<String> changeRocketName(String rocketName) throws LogsServiceUnavailableException;
}
