package fr.teama.rockethardwaremock.interfaces;

public interface IHardware {
    void rocketLaunched();

    void startSendData() throws InterruptedException;
}
