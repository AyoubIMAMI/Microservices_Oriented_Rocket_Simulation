package fr.teama.rocketdepartmentservice.services;

import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IRocketAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {

    @Autowired
    private IRocketAction rocketAction;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @KafkaListener(topics = "rocket-event-topic", groupId = "group_id")
    void rocketEvent(String event) throws RocketHardwareServiceUnavailableException {
        event = event.substring(1, event.length() - 1);
        switch (event) {
            case "stage":
                LoggerHelper.logInfo("Notification received, fuel condition reached for staging the rocket");
                rocketAction.stageRocket();
                break;
            case "enters-q":
                LoggerHelper.logInfo("Notification received, rocket enters Max Q => slow down");
                rocketAction.slowDownRocket();
                kafkaProducerService.warnWebcaster("Rocket enters Max Q => slow down");
                break;
            case "fairing-altitude":
                LoggerHelper.logInfo("Notification received, rocket reaches the orbit altitude");
                rocketAction.fairing();
                kafkaProducerService.warnWebcaster("Rocket reaches the orbit altitude => fairing");
                LoggerHelper.logInfo("The rocket department wants the second engine to cut-off");
                rocketAction.slowDownRocket();
                kafkaProducerService.warnWebcaster("Second engine cut-off");
                break;
            case "leaves-q":
                LoggerHelper.logInfo("Notification received, rocket leaves Max Q => speed up");
                rocketAction.activeStage();
                kafkaProducerService.warnWebcaster("Rocket leaves Max Q => speed up");
                break;
            default:
                LoggerHelper.logWarn("Unknown event received: " + event);
        }

    }
}