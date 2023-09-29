package fr.teama.stagehardwaremockservice.models;

import fr.teama.stagehardwaremockservice.controllers.dto.StageDataDTO;
import fr.teama.stagehardwaremockservice.controllers.dto.StageFullStateDTO;

import java.time.LocalDateTime;

public class StageData {

    private int stageLevel;

    private Double fuel;

    private boolean isActivated;

    private Double altitude;

    private Double speed;

    private Double acceleration;

    private LocalDateTime timestamp;

    public StageData(StageFullStateDTO fullStage) {
        StageDataDTO stageData = fullStage.getStageData();
        this.stageLevel = stageData.getStageLevel();
        this.fuel = stageData.getFuel();
        this.isActivated = stageData.isActivated();
        this.altitude = fullStage.getAltitude();
        this.speed = fullStage.getSpeed() * 0.6; // Loss of speed due to staging
    }

    public StageData() {

    }

    public int getStageLevel() {
        return stageLevel;
    }

    public Double getFuel() {
        return fuel;
    }

    public void setFuel(Double fuel) {
        this.fuel = fuel;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public String toString() {
        return "StageData{" +
                "stageLevel=" + stageLevel +
                ", altitude=" + altitude +
                ", speed=" + speed +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
