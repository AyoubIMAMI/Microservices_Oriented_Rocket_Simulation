package fr.teama.logsservice.components;

import fr.teama.logsservice.models.MissionLog;
import fr.teama.logsservice.repositories.MissionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogsManager {
    @Autowired
    MissionLogRepository missionLogRepository;

    public void saveLog(String serviceName, String text) {
         MissionLog missionLog = new MissionLog(serviceName, text);
         missionLogRepository.save(missionLog);
    }
}
