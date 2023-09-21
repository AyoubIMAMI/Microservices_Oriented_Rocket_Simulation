package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.entities.Notification;
import fr.teama.telemetryservice.entities.RocketData;
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
        notificationRepository.save(notification);
    }

    @Override
    public void changeInData(RocketData rocketData) {
        for (Notification notification:notificationRepository.findAll()){
            if (notification.getFuel()!=null&& notification.getFuel()>rocketData.getStageByLevel(1).getFuel()){

            }
        }
    }
}
