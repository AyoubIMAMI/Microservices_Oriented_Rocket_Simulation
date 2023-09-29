package fr.teama.rockethardwareservice.interfaces.proxy;

import fr.teama.rockethardwareservice.exceptions.StageHardwareServiceUnavailableException;
import fr.teama.rockethardwareservice.models.StageData;

public interface IStageHardwareProxy {


    void startLogging(StageData stageToDetach, Double altitude, Double speed) throws StageHardwareServiceUnavailableException;
}
