package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.ExecutiveServiceUnavailableException;

public interface IExecutiveProxy {

    void missionStartNotification() throws ExecutiveServiceUnavailableException;
}
