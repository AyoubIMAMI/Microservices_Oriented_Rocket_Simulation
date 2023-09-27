package fr.teama.rockethardwareservice.components;

import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.helpers.LoggerHelper;
import fr.teama.rockethardwareservice.interfaces.IHardware;
import fr.teama.rockethardwareservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.rockethardwareservice.models.RocketData;
import fr.teama.rockethardwareservice.models.StageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class Hardware implements IHardware {

    @Autowired
    ITelemetryProxy telemetryProxy;

    private final long updateDelay = 1;
    RocketData rocket = new RocketData(List.of(new StageData(1, 200), new StageData(2, 100)));


    boolean sendLog = false;


    @Override
    public void startLogging() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("Start logging");

        final int[] stageLevel = {1};

        sendLog = true;

        while (sendLog) {
            rocket.setAltitude(rocket.getAltitude() +  new Random().nextDouble() * 100);
            rocket.setSpeed(rocket.getSpeed() + new Random().nextDouble() * 10);
            rocket.getStages().forEach(stage -> {
                if (stage.getStageLevel() == stageLevel[0]) {
                    if (!stage.isActivated()) {
                        stage.setActivated(true);
                    }
                    stage.setFuel(stage.getFuel() - new Random().nextDouble() * 10);
                    if (stage.getFuel() <= 0) {
                        stage.setFuel(0);
                        stage.setActivated(false);
                        stage.setDetached(true);
                        stageLevel[0]++;
                    }
                }
            });

            try {
                rocket.setTimestamp(java.time.LocalDateTime.now());
                telemetryProxy.sendRocketData(rocket);
                TimeUnit.SECONDS.sleep(updateDelay);
            } catch (InterruptedException e) {
                LoggerHelper.logError(e.toString());
            }
        }
    }

    @Override
    public void stopLogging() {
        LoggerHelper.logInfo("Stop logging");
        sendLog = false;
    }

    @Override
    public void sabotageTheRocket() {
        LoggerHelper.logWarn("Rocket successfully sabotaged");
        rocket.setInNormalState(false);
    }
}
