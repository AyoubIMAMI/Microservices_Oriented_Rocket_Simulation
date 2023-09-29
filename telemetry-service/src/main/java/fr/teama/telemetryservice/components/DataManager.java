package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.exceptions.ExecutiveServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.MissionServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import fr.teama.telemetryservice.models.RocketData;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.DataSaver;
import fr.teama.telemetryservice.models.StageData;
import fr.teama.telemetryservice.repository.RocketDataRepository;
import fr.teama.telemetryservice.repository.StageDataRepository;
import fr.teama.telemetryservice.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DataManager implements DataSaver {

    @Autowired
    ITelemetryNotifier trackingHandler;

    @Autowired
    RocketDataRepository rocketDataRepository;

    @Autowired
    StageDataRepository stageDataRepository;

    @Autowired
    TrackingRepository trackingRepository;

    @Override
    public ResponseEntity<String> saveRocketData(RocketData rocketData) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException {
        trackingHandler.verifyRocketData(rocketData);
        stageDataRepository.saveAll(rocketData.getStages());
        rocketDataRepository.save(rocketData);
        return ResponseEntity.ok().body("Rocket data saved");
    }

    @Override
    public ResponseEntity<String> saveStageData(StageData stageData) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException {
        trackingHandler.verifyStageData(stageData);
        stageDataRepository.save(stageData);
        return ResponseEntity.ok().body("Stage data saved");
    }

    @Override
    public ResponseEntity<String> resetDB() {
        rocketDataRepository.deleteAll();
        stageDataRepository.deleteAll();
        trackingRepository.deleteAll();
        return ResponseEntity.ok().body("Database reset");
    }
}
