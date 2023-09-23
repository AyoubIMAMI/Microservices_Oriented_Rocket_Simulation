package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.models.Notification;
import fr.teama.telemetryservice.models.RocketData;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import fr.teama.telemetryservice.interfaces.proxy.IPayloadProxy;
import fr.teama.telemetryservice.interfaces.proxy.IRocketStageProxy;
import fr.teama.telemetryservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackingHandler implements ITelemetryNotifier {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    IPayloadProxy payloadProxy;
    @Autowired
    IRocketStageProxy rocketStageProxy;

    @Override
    public void trackingNotify(Notification notification, String serviceToBeNotified) {
        LoggerHelper.logInfo("Tracking conditions save for " + serviceToBeNotified + " as " + notification.toString());
        notificationRepository.save(notification);
    }

    @Override
    public void changeInData(RocketData rocketData) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException {
        for (Notification notification:notificationRepository.findAll()){
            if (notification.getFuel()!=null&& rocketData.getStageByLevel(1).getFuel()<=notification.getFuel()){
                LoggerHelper.logInfo("Fuel condition reached:" );
                LoggerHelper.logInfo("Rocket infos: "+rocketData);
                LoggerHelper.logInfo("Condition to send notification: "+notification);
                notificationRepository.delete(notification);
                notifyService(notification.getServiceToBeNotified());

            }
            else if (notification.getHeight()!=null&& rocketData.getAltitude()>= notification.getHeight()){
                LoggerHelper.logInfo("Altitude condition reached:" );
                LoggerHelper.logInfo("Rocket infos: "+rocketData);
                LoggerHelper.logInfo("Condition to send notification: "+notification);
                notificationRepository.delete(notification);
                notifyService(notification.getServiceToBeNotified());
            }
        }
    }
    private void notifyService(String serviceName) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException {
        if (serviceName.equals("rocket-department")){
            rocketStageProxy.fuelLevelReached();
        }
        else if(serviceName.equals("payload")){
            payloadProxy.heightReached();
        }
    }
}
