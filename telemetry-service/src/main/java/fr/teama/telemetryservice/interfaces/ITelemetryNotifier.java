package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.models.RobotData;
import fr.teama.telemetryservice.models.StageData;
import fr.teama.telemetryservice.models.Tracking;
import fr.teama.telemetryservice.models.RocketData;

public interface ITelemetryNotifier {

    Tracking trackingNotify(Tracking tracking);

    void verifyRocketData(RocketData rocketData);

    void verifyStageData(StageData stageData);

    void verifyRobotData(RobotData robotData);
}
