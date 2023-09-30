package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.exceptions.ExecutiveServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.proxy.IExecutiveProxy;
import fr.teama.telemetryservice.models.*;
import fr.teama.telemetryservice.exceptions.MissionServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.proxy.IMissionProxy;
import fr.teama.telemetryservice.models.RocketData;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import fr.teama.telemetryservice.interfaces.proxy.IPayloadProxy;
import fr.teama.telemetryservice.interfaces.proxy.IRocketStageProxy;
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
    IRocketStageProxy rocketStageProxy;

    @Autowired
    IMissionProxy missionProxy;

    @Autowired
    IExecutiveProxy executiveProxy;

    @Override
    public Tracking trackingNotify(Tracking tracking) {
        LoggerHelper.logInfo("Tracking conditions save for " + tracking.getServiceToBeNotified() + " service as " + tracking);
        return trackingRepository.save(tracking);
    }

    @Override
    public void verifyRocketData(RocketData rocketData) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException {
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
    public void verifyStageData(StageData stageData) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException {
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

    private void notifyService(Tracking tracking) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException {
        switch (tracking.getServiceToBeNotified()) {
            case "rocket-department":
                switch (tracking.getRouteToNotify()) {
                    case "/rocket/stage":
                        rocketStageProxy.fuelLevelReached();
                        break;
                    case "/rocket/enters-q":
                        rocketStageProxy.heightReached(tracking);
                        break;
                    case "/rocket/leaves-q":
                        rocketStageProxy.heightReached(tracking);
                        break;
                }
                break;
            case "payload":
                payloadProxy.heightReached();
                break;
            case "mission":
                missionProxy.specificStatusDetected();
                break;
            case "executive":
                executiveProxy.notifyStageLanded();
                break;
            default:
                break;
        }
    }
}
