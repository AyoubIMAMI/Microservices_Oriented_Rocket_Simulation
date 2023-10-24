package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.exceptions.*;
import fr.teama.telemetryservice.interfaces.proxy.*;
import fr.teama.telemetryservice.models.*;
import fr.teama.telemetryservice.models.RocketData;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import fr.teama.telemetryservice.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackingHandler implements ITelemetryNotifier {
    @Autowired
    TrackingRepository trackingRepository;

    @Autowired
    IPayloadProxy payloadProxy;

    @Autowired
    IRocketDepartmentProxy rocketDepartmentProxy;

    @Autowired
    IMissionProxy missionProxy;

    @Autowired
    IExecutiveProxy executiveProxy;

    @Autowired
    IRobotDepartmentProxy robotDepartmentProxy;

    @Override
    public Tracking trackingNotify(Tracking tracking) {
        LoggerHelper.logInfo("Tracking conditions save for " + tracking.getServiceToBeNotified() + " service as " + tracking);
        return trackingRepository.save(tracking);
    }

    @Override
    public void verifyRocketData(RocketData rocketData) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException {
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
    public void verifyStageData(StageData stageData) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException {
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
    public void verifyRobotData(RobotData robotData) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException {
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
            default -> 0.0;
        };
    }

    private void notifyService(Tracking tracking) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException {
        switch (tracking.getServiceToBeNotified()) {
            case "rocket-department":
                switch (tracking.getRouteToNotify()) {
                    case "/rocket/stage":
                        rocketDepartmentProxy.fuelLevelReached();
                        break;
                    case "/rocket/enters-q", "/rocket/fairing-altitude", "/rocket/leaves-q":
                        rocketDepartmentProxy.heightReached(tracking);
                        break;
                }
                break;
            case "payload":
                payloadProxy.heightReached();
                break;
            case "mission":
                missionProxy.specificStatusDetected(tracking.getRouteToNotify());
                break;
            case "executive":
                executiveProxy.notifyStageLanded();
                break;
            case "robot-department":
                switch (tracking.getRouteToNotify()) {
                    case "/robot/drop":
                        robotDepartmentProxy.notifyHeightReached();
                        break;
                    case "/robot/landed":
                        robotDepartmentProxy.landedSuccessfully();
                        break;
                }
                break;
            default:
                break;
        }
    }
}
