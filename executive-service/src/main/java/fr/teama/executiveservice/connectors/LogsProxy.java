package fr.teama.executiveservice.connectors;

import fr.teama.executiveservice.connectors.externalDTO.MissionLogDTO;
import fr.teama.executiveservice.helpers.LoggerHelper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
public class LogsProxy {
    private static String apiBaseUrlHostAndPort = "http://logs-service:8080/api";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static ResponseEntity<String> saveNewLog(String serviceName, String text) {
        try {
            MissionLogDTO missionLogDTO = new MissionLogDTO(serviceName, text, LocalDateTime.now());
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/logs/save", missionLogDTO, String.class);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new LogsServiceUnavailableException();
            return new ResponseEntity<>(e.toString(), HttpStatusCode.valueOf(500));
        }
    }
}
