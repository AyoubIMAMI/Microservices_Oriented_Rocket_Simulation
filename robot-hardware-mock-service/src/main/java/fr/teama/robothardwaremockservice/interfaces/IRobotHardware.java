package fr.teama.robothardwaremockservice.interfaces;


import fr.teama.robothardwaremockservice.models.Position;

public interface IRobotHardware {

    void startLoggingLanding(Position position);

    void startLoggingMovement(Position positionToReach);

    void stopLogging();

    void takeSamples();
}
