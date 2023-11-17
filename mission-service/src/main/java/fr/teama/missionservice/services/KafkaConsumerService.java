package fr.teama.missionservice.services;

import fr.teama.missionservice.exceptions.LogsServiceUnavailableException;
import fr.teama.missionservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.IMissionManager;
import fr.teama.missionservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private IMissionManager missionManager;
    @Autowired
    IRocketHardwareProxy rocketHardwareProxy;

    @KafkaListener(topics = "mission-event-topic", groupId = "group_id")
    void missionEvent(String event) throws RocketHardwareServiceUnavailableException, LogsServiceUnavailableException {
        event = event.substring(1, event.length() - 1);
        switch (event) {
            case "rocket-hardware-destruction":
                rocketHardwareProxy.rocketDestruction();
                missionManager.missionFailed();
                break;
            case "rocket-pressure-anomaly":
                LoggerHelper.logWarn("Pressure anomaly detected");
                break;
            default:
                LoggerHelper.logWarn("Unknown event received");
        }
    }
}