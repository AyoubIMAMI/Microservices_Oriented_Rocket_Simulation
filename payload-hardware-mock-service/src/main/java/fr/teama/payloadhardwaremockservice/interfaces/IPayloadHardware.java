package fr.teama.payloadhardwaremockservice.interfaces;

import fr.teama.payloadhardwaremockservice.models.Position;

public interface IPayloadHardware {
    void startOrbitalPosDispatch(Position position);

    void stopOrbitalPosDispatch();
}
