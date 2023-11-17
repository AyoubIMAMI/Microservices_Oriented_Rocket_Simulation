package fr.teama.payloadservice.services;

import fr.teama.payloadservice.controllers.dto.PayloadDataDTO;
import fr.teama.payloadservice.controllers.dto.PositionDTO;
import fr.teama.payloadservice.exceptions.MissionServiceUnavailableException;
import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.payloadservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.payloadservice.helpers.LoggerHelper;
import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import fr.teama.payloadservice.interfaces.PayloadDataHandler;
import fr.teama.payloadservice.interfaces.proxy.IRocketHardwareProxy;
import fr.teama.payloadservice.models.PayloadData;
import fr.teama.payloadservice.models.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class KafkaConsumerService {

    @Autowired
    private IPayloadReleaser payloadReleaser;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private IRocketHardwareProxy rocketHardwareProxy;

    @Autowired
    private PayloadDataHandler payloadDataHandler;


    @KafkaListener(topics = "payload-event-topic", groupId = "group_id")
    void payloadEvent(String event) throws MissionServiceUnavailableException, RocketHardwareServiceUnavailableException {
        event = event.substring(1, event.length() - 1);
        switch (event) {
            case "height-reached":
                LoggerHelper.logInfo("Notification received, requesting to drop the payload");
                kafkaProducerService.warnWebcaster("Payload drop requested");
                payloadReleaser.dropPayload();
                rocketHardwareProxy.activeEngine();
                break;
            default:
                LoggerHelper.logWarn("Unknown event received: " + event);
        }
    }

    @KafkaListener(topics = "payload-data-topic", groupId = "group_id", containerFactory = "messagePayloadDataListener")
    public void payloadData(PayloadDataDTO payloadDataDTO) throws PayloadHardwareServiceUnavaibleException {
        String rocketName = payloadDataDTO.getRocketName();
        PositionDTO positionDTO = payloadDataDTO.getPosition();
        LocalDateTime timestamp = payloadDataDTO.getTimestamp();
        Position position = new Position(positionDTO.getX(), positionDTO.getY(), positionDTO.getAltitude());
        payloadDataHandler.saveDataPayload(new PayloadData(rocketName, position, timestamp));
    }
}