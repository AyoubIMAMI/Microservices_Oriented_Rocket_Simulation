package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.exceptions.*;
import fr.teama.telemetryservice.models.RobotData;
import fr.teama.telemetryservice.models.StageData;
import fr.teama.telemetryservice.models.Tracking;
import fr.teama.telemetryservice.models.RocketData;

public interface ITelemetryNotifier {

    Tracking trackingNotify(Tracking tracking);

    void verifyRocketData(RocketData rocketData) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException;

    void verifyStageData(StageData stageData) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException;

    void verifyRobotData(RobotData robotData) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException ;
}
