package fr.teama.logsservice.components;

import fr.teama.logsservice.helpers.LoggerHelper;
import fr.teama.logsservice.interfaces.ILogsManager;
import fr.teama.logsservice.models.MissionLog;
import fr.teama.logsservice.repositories.MissionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LogsManager implements ILogsManager {
    @Autowired
    MissionLogRepository missionLogRepository;

    @Override
    public void saveLog(String serviceName, String text, LocalDateTime date) {
        MissionLog missionLog = new MissionLog(serviceName, text, date);
        missionLogRepository.save(missionLog);
//        LoggerHelper.logInfo("New mission log saved : " + missionLog);
        missionLogRepository.save(missionLog);
    }

    @Override
    public List<MissionLog> getAllLogs() {
        return missionLogRepository.findAll();
    }

    @Override
    public void resetDb() {
        missionLogRepository.deleteAll();
    }
}
