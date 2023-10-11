package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.controllers.dto.PayloadDataDTO;
import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import fr.teama.telemetryservice.controllers.dto.StageDataDTO;
import fr.teama.telemetryservice.exceptions.ExecutiveServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.MissionServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.DataSender;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import fr.teama.telemetryservice.interfaces.proxy.IPayloadProxy;
import fr.teama.telemetryservice.models.PayloadData;
import fr.teama.telemetryservice.models.RocketData;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.DataSaver;
import fr.teama.telemetryservice.models.RocketName;
import fr.teama.telemetryservice.models.StageData;
import fr.teama.telemetryservice.repository.RocketDataRepository;
import fr.teama.telemetryservice.repository.RocketNameRepository;
import fr.teama.telemetryservice.repository.StageDataRepository;
import fr.teama.telemetryservice.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DataManager implements DataSaver, DataSender {

    @Autowired
    ITelemetryNotifier trackingHandler;

    @Autowired
    RocketDataRepository rocketDataRepository;

    @Autowired
    StageDataRepository stageDataRepository;

    @Autowired
    TrackingRepository trackingRepository;

    @Autowired
    RocketNameRepository rocketNameRepository;

    @Autowired
    IPayloadProxy payloadProxy;

    @Override
    public ResponseEntity<String> saveRocketData(RocketDataDTO rocketDataDTO) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException {
        RocketData rocketData = new RocketData(rocketNameRepository.findTopByOrderByIdDesc().getName(), rocketDataDTO);
        trackingHandler.verifyRocketData(rocketData);
        stageDataRepository.saveAll(rocketData.getStages());
        rocketDataRepository.save(rocketData);
        return ResponseEntity.ok().body("Rocket data saved");
    }

    @Override
    public ResponseEntity<String> saveStageData(StageDataDTO stageDataDTO) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException {
        StageData stageData = new StageData(rocketNameRepository.findTopByOrderByIdDesc().getName(), stageDataDTO);
        trackingHandler.verifyStageData(stageData);
        stageDataRepository.save(stageData);
        return ResponseEntity.ok().body("Stage data saved");
    }

    @Override
    public ResponseEntity<String> resetTracking() {
        trackingRepository.deleteAll();
        return ResponseEntity.ok().body("Database reset");
    }

    @Override
    public ResponseEntity<String> changeRocketName(String rocketNameStr) {
        RocketName rocketName = new RocketName(rocketNameStr);
        rocketNameRepository.save(rocketName);
        return ResponseEntity.ok().body("Rocket name changed for " + rocketName);
    }

    @Override
    public ResponseEntity<String> sendPayloadData(PayloadDataDTO payloadDataDTO) throws PayloadServiceUnavailableException {
        PayloadData payloadData = new PayloadData(rocketNameRepository.findTopByOrderByIdDesc().getName(), payloadDataDTO);
        return payloadProxy.sendData(payloadData);
    }
}
