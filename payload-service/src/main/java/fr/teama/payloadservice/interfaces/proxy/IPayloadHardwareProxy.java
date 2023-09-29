package fr.teama.payloadservice.interfaces.proxy;

import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;

public interface IPayloadHardwareProxy {
    void startOrbitalPosDispatch() throws  PayloadHardwareServiceUnavaibleException;
    void stopOrbitalPosDispatch() throws  PayloadHardwareServiceUnavaibleException;

}
