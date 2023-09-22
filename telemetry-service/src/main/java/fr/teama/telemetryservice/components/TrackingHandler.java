package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.entities.Notification;
import fr.teama.telemetryservice.entities.RocketData;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.ILoggerComponent;
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
    @Autowired
    ILoggerComponent logger;

    @Override
    public void trackingNotify(Notification notification, String serviceToBeNotified) {
        logger.logInfo("Tracking conditions save for " + serviceToBeNotified + " as " + notification.toString());
        notificationRepository.save(notification);
    }

    @Override
    public void changeInData(RocketData rocketData) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException {
        for (Notification notification:notificationRepository.findAll()){
            if (notification.getFuel()!=null&& notification.getFuel()>rocketData.getStageByLevel(1).getFuel()){
                System.out.println("Fuel condition reached:" );
                System.out.println("Rocket infos: "+rocketData);
                System.out.println("Condition to send notification: "+notification);
                notifyService(notification.getServiceToBeNotified());

            }
            else if (notification.getHeight()!=null&& notification.getHeight()>rocketData.getAltitude()){
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
