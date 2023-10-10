package fr.teama.payloadservice.interfaces.proxy;

import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;

public interface IPayloadHardwareProxy {
    void stopOrbitalPosDispatch() throws  PayloadHardwareServiceUnavaibleException;
}
