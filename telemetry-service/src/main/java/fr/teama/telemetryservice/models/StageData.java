package fr.teama.telemetryservice.models;

import fr.teama.telemetryservice.controllers.dto.StageDataDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Embeddable
@Entity
public class StageData {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Stage must have a level")
    private int stageLevel;
    private Double fuel;

    private boolean isActivated;

    private Double altitude;

    private Double speed;

    private Double acceleration;

    private LocalDateTime timestamp;

    public StageData() {

    }

    public StageData(StageDataDTO stageDTO) {
        this.stageLevel = stageDTO.getStageLevel();
        this.fuel = stageDTO.getFuel();
        this.isActivated = stageDTO.isActivated();
        this.altitude = stageDTO.getAltitude();
        this.speed = stageDTO.getSpeed();
        this.acceleration = stageDTO.getAcceleration();
        this.timestamp = stageDTO.getTimestamp();
    }

    @Override
    public String toString() {
        return "StageData{" +
                "stageLevel=" + stageLevel +
                ", fuel=" + fuel +
                ", isActivated=" + isActivated +
                ", altitude=" + altitude +
                ", speed=" + speed +
                ", acceleration=" + acceleration +
                ", timestamp=" + timestamp +
                '}';
    }

    public int getStageLevel() {
        return stageLevel;
    }

    public void setStageLevel(int stageLevel) {
        this.stageLevel = stageLevel;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
