package fr.teama.telemetryservice.components;

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

    @Override
    public Tracking trackingNotify(Tracking tracking) {
        LoggerHelper.logInfo("Tracking conditions save for " + tracking.getServiceToBeNotified() + " as " + tracking.toString());
        return trackingRepository.save(tracking);
    }

    @Override
    public void verifyRocketData(RocketData rocketData) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException, MissionServiceUnavailableException {
        for (Tracking tracking: trackingRepository.findByCategory(TrackingCategory.ROCKET)){
            boolean allConditionsReached = true;

            for (TrackItem trackItem: tracking.getData()) {
                Double dataToCheck = getRocketDataToCheck(trackItem.getFieldToTrack(), rocketData);
                if (!trackItem.verifyCondition(dataToCheck)) {
                    allConditionsReached = false;
                }
            }

            if (allConditionsReached) {
                LoggerHelper.logInfo("All conditions reached for tracking:" + tracking.toString());
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
            default -> null;
        };
    }

    private void notifyService(Tracking tracking) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException, MissionServiceUnavailableException {
        switch (tracking.getServiceToBeNotified()) {
            case "rocket-department":
                // TODO: Add inner switch to manage multiple route and make the call with the right proxy function
                rocketStageProxy.fuelLevelReached();
                break;
            case "payload":
                // TODO: Add inner switch to manage multiple route and make the call with the right proxy function
                payloadProxy.heightReached();
                break;
            case "mission":
                missionProxy.specificStatusDetected();
            default:
                break;
        }
    }
}
