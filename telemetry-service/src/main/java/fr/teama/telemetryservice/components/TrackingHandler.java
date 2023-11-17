package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.models.*;
import fr.teama.telemetryservice.models.RocketData;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import fr.teama.telemetryservice.repository.TrackingRepository;
import fr.teama.telemetryservice.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackingHandler implements ITelemetryNotifier {
    @Autowired
    TrackingRepository trackingRepository;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @Override
    public Tracking trackingNotify(Tracking tracking) {
        LoggerHelper.logInfo("Tracking conditions save for " + tracking.getServiceToBeNotified() + " service as " + tracking);
        return trackingRepository.save(tracking);
    }

    @Override
    public void verifyRocketData(RocketData rocketData) {
        for (Tracking tracking: trackingRepository.findByCategory(TrackingCategory.ROCKET)){
            boolean allConditionsReached = true;

            for (TrackItem trackItem: tracking.getData()) {
                Double dataToCheck = getRocketDataToCheck(trackItem.getFieldToTrack(), rocketData);
                if (!trackItem.verifyCondition(dataToCheck)) {
                    allConditionsReached = false;
                }
            }

            if (allConditionsReached) {
                LoggerHelper.logInfo("All conditions reached for tracking:" + tracking);
                notifyService(tracking);
                trackingRepository.delete(tracking);
            }
        }
    }

    @Override
    public void verifyStageData(StageData stageData) {
        for (Tracking tracking: trackingRepository.findByCategory(TrackingCategory.INDEPENDENT_STAGE)){
            boolean allConditionsReached = true;

            for (TrackItem trackItem: tracking.getData()) {
                Double dataToCheck = getStageDataToCheck(trackItem.getFieldToTrack(), stageData);
                if (!trackItem.verifyCondition(dataToCheck)) {
                    allConditionsReached = false;
                }
            }

            if (allConditionsReached) {
                LoggerHelper.logInfo("All conditions reached for tracking:" + tracking);
                notifyService(tracking);
                trackingRepository.delete(tracking);
            }
        }
    }

    @Override
    public void verifyRobotData(RobotData robotData) {
        for (Tracking tracking: trackingRepository.findByCategory(TrackingCategory.ROBOT)){
            boolean allConditionsReached = true;

            for (TrackItem trackItem: tracking.getData()) {
                Double dataToCheck = getRobotDataToCheck(trackItem.getFieldToTrack(), robotData);
                if (!trackItem.verifyCondition(dataToCheck)) {
                    allConditionsReached = false;
                }
            }

            if (allConditionsReached) {
                LoggerHelper.logInfo("All conditions reached for tracking:" + tracking);
                notifyService(tracking);
                trackingRepository.delete(tracking);
            }
        }
    }

    private Double getRocketDataToCheck(TrackingField fieldToTrack, RocketData rocketData) {
        return switch (fieldToTrack) {
            case HEIGHT -> rocketData.getAltitude();
            case FUEL -> rocketData.getStageByLevel(1).getFuel();
            case SPEED -> rocketData.getSpeed();
            case STATUS -> rocketData.getStatus();
            default -> 0.0;
        };
    }

    private Double getStageDataToCheck(TrackingField fieldToTrack, StageData stageData) {
        return switch (fieldToTrack) {
            case HEIGHT -> stageData.getAltitude();
            case FUEL -> stageData.getFuel();
            case SPEED -> stageData.getSpeed();
            default -> 0.0;
        };
    }

    private Double getRobotDataToCheck(TrackingField fieldToTrack, RobotData robotData) {
        return switch (fieldToTrack) {
            case HEIGHT -> robotData.getPosition().getAltitude();
            case X -> robotData.getPosition().getX();
            case Y -> robotData.getPosition().getY();
            default -> 0.0;
        };
    }

    private void notifyService(Tracking tracking) {
        switch (tracking.getServiceToBeNotified()) {
            case "rocket-department":
                switch (tracking.getEventDataType()) {
                    case "stage":
                        kafkaProducerService.fuelLevelReached();
                        break;
                    case "enters-q", "fairing-altitude", "leaves-q":
                        kafkaProducerService.heightReached(tracking);
                        break;
                }
                break;
            case "payload":
                kafkaProducerService.payloadHeightReached();
                break;
            case "mission":
                kafkaProducerService.specificStatusDetected(tracking.getEventDataType());
                break;
            case "executive":
                kafkaProducerService.notifyStageLanded();
                break;
            case "robot-department":
                switch (tracking.getEventDataType()) {
                    case "drop":
                        kafkaProducerService.robotNotifyHeightReached();
                        break;
                    case "landed":
                        kafkaProducerService.robotLandedSuccessfully();
                        break;
                    case "reached-position":
                        kafkaProducerService.robotReachedPositionSuccessfully();
                        break;
                }
                break;
            default:
                break;
        }
    }
}
