package fr.teama.payloadhardwaremockservice.components;

import fr.teama.payloadhardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadhardwaremockservice.helpers.LoggerHelper;
import fr.teama.payloadhardwaremockservice.interfaces.IHardware;
import fr.teama.payloadhardwaremockservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.payloadhardwaremockservice.models.PayloadData;
import fr.teama.payloadhardwaremockservice.models.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class Hardware implements IHardware {

    @Autowired
    ITelemetryProxy telemetryProxy;

    private final long updateDelay = 1;
    PayloadData payload;
    boolean sendLog = false;

    @Override
    public void startOrbitalPosDispatch() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("Start orbital position dispatch");

        payload = new PayloadData();
        sendLog = true;

        while (sendLog) {
            Position position= payload.getPosition();
            Random random = new Random();
            position.setX(position.getX() +  random.nextDouble() * 10);
            position.setY(position.getY() +  (random.nextBoolean()? random.nextDouble() * 10: -random.nextDouble() * 10));
            position.setAltitude(position.getAltitude() +  (random.nextBoolean()? random.nextDouble() * 10: -random.nextDouble() * 10));

            try {
                payload.setTimestamp(java.time.LocalDateTime.now());
                telemetryProxy.sendPayloadData(payload);
                TimeUnit.SECONDS.sleep(updateDelay);
            } catch (InterruptedException e) {
                LoggerHelper.logError(e.toString());
            }
        }
    }

    @Override
    public void stopOrbitalPosDispatch() {
        LoggerHelper.logInfo("Stop orbital position dispatch");
        sendLog = false;
    }
}
