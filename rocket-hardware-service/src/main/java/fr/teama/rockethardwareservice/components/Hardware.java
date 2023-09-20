package fr.teama.rockethardwareservice.components;

import fr.teama.rockethardwareservice.interfaces.IHardware;
import fr.teama.rockethardwareservice.models.Rocket;
import fr.teama.rockethardwareservice.models.Stage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class Hardware implements IHardware {

    long delay = 1;

    Rocket rocket = new Rocket(List.of(new Stage(1, 200), new Stage(2, 100)));

    @Override
    public void rocketLaunched() {

        final int[] stageLevel = {1};

        while (true) {
            rocket.setAltitude(rocket.getAltitude() +  new Random().nextDouble() * 100);
            rocket.setSpeed(rocket.getSpeed() + new Random().nextDouble() * 10);
            rocket.getStages().forEach(stage -> {
                if (stage.getStageId() == stageLevel[0]) {
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
                TimeUnit.SECONDS.sleep(delay);
                System.out.println(rocket);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
