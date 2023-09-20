package fr.teama.rockethardwareservice.components;

import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.interfaces.IHardware;
import fr.teama.rockethardwareservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.rockethardwareservice.models.Rocket;
import fr.teama.rockethardwareservice.models.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class Hardware implements IHardware {

    @Autowired
    ITelemetryProxy telemetryProxy;

    long updateDelay = 1;

    boolean sendLog = false;

    Rocket rocket = new Rocket(List.of(new Stage(1, 200), new Stage(2, 100)));

    @Override
    public void startLogging() throws TelemetryServiceUnavailableException {

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
                TimeUnit.SECONDS.sleep(updateDelay);
                telemetryProxy.sendRocketData(rocket);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopLogging() {
        sendLog = false;
    }
}
