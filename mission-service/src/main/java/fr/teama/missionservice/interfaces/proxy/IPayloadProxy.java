package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.PayloadServiceUnavailableException;

public interface IPayloadProxy {

    void missionStartNotify() throws PayloadServiceUnavailableException;
}
