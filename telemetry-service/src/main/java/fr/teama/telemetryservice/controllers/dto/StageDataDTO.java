package fr.teama.telemetryservice.controllers.dto;

import java.time.LocalDateTime;

public class StageDataDTO {

    private int stageLevel;

    private Double fuel;

    private boolean isActivated;

    private PositionDTO position;

    private Double speed;

    private Double acceleration;

    private Double angle;

    private LocalDateTime timestamp;

    private boolean legsDeployed;

    public StageDataDTO() {
    }

    @Override
    public String toString() {
        return "StageData{" +
                "stageLevel=" + stageLevel +
                ", fuel=" + fuel +
                ", isActivated=" + isActivated +
                '}';
    }

    public String toStringComplete() {
        return "StageData{" +
                "fuel=" + fuel +
                ", isActivated=" + isActivated +
                ", position=" + position +
                ", speed=" + speed +
                ", angle=" + roundDouble(angle) +
                ", legsDeployed=" + legsDeployed +
                '}';
    }

    public void setFuel(Double fuel) {
        this.fuel = fuel;
    }

    public int getStageLevel() {
        return stageLevel;
    }

    public Double getFuel() {
        return fuel;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public void setStageLevel(int stageLevel) {
        this.stageLevel = stageLevel;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public boolean isLegsDeployed() {
        return legsDeployed;
    }

    public void setLegsDeployed(boolean legsDeployed) {
        this.legsDeployed = legsDeployed;
    }

    private Double roundDouble(Double value) {
        return Math.round(value * 10.0) / 10.0;
    }
}
