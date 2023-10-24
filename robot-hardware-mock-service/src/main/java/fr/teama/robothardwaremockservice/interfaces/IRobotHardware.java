package fr.teama.robothardwaremockservice.interfaces;


import fr.teama.robothardwaremockservice.models.Position;

public interface IRobotHardware {

    void startLogging(Position position);

    void stopLogging();
}
