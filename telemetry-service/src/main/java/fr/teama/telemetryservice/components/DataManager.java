package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.entities.RocketData;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.DataSaver;
import fr.teama.telemetryservice.repository.RocketDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DataManager implements DataSaver {
    @Autowired
    TrackingHandler trackingHandler;
    @Autowired
    RocketDataRepository rocketDataRepository;
    @Override
    public ResponseEntity<String> saveData(RocketData rocketData) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException {
        trackingHandler.changeInData(rocketData);
        rocketDataRepository.save(rocketData);
        return ResponseEntity.ok().body("saved");
    }
}
