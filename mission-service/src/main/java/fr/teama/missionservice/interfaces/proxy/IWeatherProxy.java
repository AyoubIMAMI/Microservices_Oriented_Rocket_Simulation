package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;

public interface IWeatherProxy {

    String getWeatherStatus() throws WeatherServiceUnavailableException;

}
