package fr.teama.missionservice.connectors;


import fr.teama.missionservice.connectors.externalDTO.MissionLogDTO;
import fr.teama.missionservice.models.SavedMissionLog;
import fr.teama.missionservice.exceptions.LogsServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.ILogsProxy;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LogsProxy implements ILogsProxy {
    private static String apiBaseUrlHostAndPort = "http://logs-service:8080/api";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static ResponseEntity<String> saveNewLog(String serviceName, String text) {
        try {
            MissionLogDTO missionLogDTO = new MissionLogDTO(serviceName, text, LocalDateTime.now());
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/logs/save", missionLogDTO, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatusCode.valueOf(500));
        }
    }

    @Override
    public ResponseEntity<List<SavedMissionLog>> getAllLogs() throws LogsServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Ask logs service for all logs");
            SavedMissionLog[] savedMissionLogArray = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/logs", SavedMissionLog[].class).getBody();
            assert savedMissionLogArray != null;
            return ResponseEntity.ok().body(List.of(savedMissionLogArray));
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new LogsServiceUnavailableException();
        }
    }

    @Override
    public ResponseEntity<String> changeRocketName(String rocketName) throws LogsServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Ask logs service to change rocket name to " + rocketName);
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/logs/rocket-name", rocketName, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new LogsServiceUnavailableException();
        }
    }
}
