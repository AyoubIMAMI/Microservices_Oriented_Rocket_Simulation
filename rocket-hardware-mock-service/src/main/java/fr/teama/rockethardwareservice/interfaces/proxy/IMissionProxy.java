package fr.teama.rockethardwareservice.interfaces.proxy;

import fr.teama.rockethardwareservice.exceptions.MissionServiceUnvailableException;
import org.springframework.http.ResponseEntity;

public interface IMissionProxy {
    ResponseEntity<String> warnMissionThatRocketHasCriticalAnomaly() throws MissionServiceUnvailableException;
}
