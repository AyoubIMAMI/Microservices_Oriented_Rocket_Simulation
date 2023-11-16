package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.controllers.dto.PayloadDataDTO;
import fr.teama.telemetryservice.controllers.dto.RobotDataDTO;
import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import fr.teama.telemetryservice.controllers.dto.StageDataDTO;
import fr.teama.telemetryservice.exceptions.*;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.DataSender;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import fr.teama.telemetryservice.interfaces.proxy.IPayloadProxy;
import fr.teama.telemetryservice.models.*;
import fr.teama.telemetryservice.interfaces.DataSaver;
import fr.teama.telemetryservice.repository.*;
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

    @Autowired
    RobotDataRepository robotDataRepository;

    @Override
    public ResponseEntity<String> saveRocketData(RocketDataDTO rocketDataDTO) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException {
        RocketData rocketData = new RocketData(rocketNameRepository.findTopByOrderByIdDesc().getName(), rocketDataDTO);
        trackingHandler.verifyRocketData(rocketData);
        stageDataRepository.saveAll(rocketData.getStages());
        rocketDataRepository.save(rocketData);
        return ResponseEntity.ok().body("Rocket data saved");
    }

    @Override
    public ResponseEntity<String> saveStageData(StageDataDTO stageDataDTO) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException {
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

    @Override
    public ResponseEntity<String> sendRobotDataForScientist(RobotDataDTO robotDataDTO) {
        //TODO send robot data to scientific department that will handle them
        LoggerHelper.logInfo("Robot data sample sent to scientific department: "+ robotDataDTO.getSample().toString());
        return(ResponseEntity.ok().body("Robot data sent to scientific department"));
    }

    @Override
    public ResponseEntity<String> saveRobotData(RobotDataDTO robotDataDTO) throws RocketStageServiceUnavailableException, ExecutiveServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, RobotDepartmentServiceUnavailableException {
        RobotData robotData = new RobotData(rocketNameRepository.findTopByOrderByIdDesc().getName(), robotDataDTO);
        trackingHandler.verifyRobotData(robotData);
        robotDataRepository.save(robotData);
        return ResponseEntity.ok().body("Robot data saved");
    }
}
