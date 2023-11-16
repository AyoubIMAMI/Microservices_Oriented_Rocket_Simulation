package fr.teama.logsservice.components;

import fr.teama.logsservice.interfaces.ILogsManager;
import fr.teama.logsservice.models.MissionLog;
import fr.teama.logsservice.models.RocketName;
import fr.teama.logsservice.repositories.MissionLogRepository;
import fr.teama.logsservice.repositories.RocketNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LogsManager implements ILogsManager {

    @Autowired
    MissionLogRepository missionLogRepository;

    @Autowired
    RocketNameRepository rocketNameRepository;

    @Override
    public void saveLog(String serviceName, String text, LocalDateTime date) {
        String rocketName = "default";
        if (rocketNameRepository.findTopByOrderByIdDesc() != null) {
            rocketName = rocketNameRepository.findTopByOrderByIdDesc().getName();
        }

        MissionLog missionLog = new MissionLog(rocketName, serviceName, text, date);
        missionLogRepository.save(missionLog);
    }

    @Override
    public List<MissionLog> getAllLogs() {
        String rocketName = "default";
        if (rocketNameRepository.findTopByOrderByIdDesc() != null) {
            rocketName = rocketNameRepository.findTopByOrderByIdDesc().getName();
        }
        return missionLogRepository.findAllByRocketName(rocketName);
    }

    @Override
    public void resetDb() {
        missionLogRepository.deleteAll();
    }

    @Override
    public ResponseEntity<String> changeRocketName(String rocketNameStr) {
        RocketName rocketName = new RocketName(rocketNameStr);
        rocketNameRepository.save(rocketName);
        return ResponseEntity.ok().body("Rocket name changed for " + rocketName);
    }
}
