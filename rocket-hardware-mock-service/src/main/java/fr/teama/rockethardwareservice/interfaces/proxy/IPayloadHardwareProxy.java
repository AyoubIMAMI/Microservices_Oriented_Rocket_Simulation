package fr.teama.rockethardwareservice.interfaces.proxy;


import fr.teama.rockethardwareservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.rockethardwareservice.models.Position;

public interface IPayloadHardwareProxy {
    void startOrbitalPosDispatch(Position position) throws PayloadHardwareServiceUnavaibleException;

}
