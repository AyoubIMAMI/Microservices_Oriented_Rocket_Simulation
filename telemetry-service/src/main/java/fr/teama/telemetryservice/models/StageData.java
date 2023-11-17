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

    String rocketName;

    @NotNull(message = "Stage must have a level")
    private int stageLevel;

    private Double fuel;

    private boolean isActivated;

    private Position position;

    private Double speed;

    private Double acceleration;

    private Double angle;

    private LocalDateTime timestamp;

    private boolean legsDeployed;

    public StageData() {

    }

    public StageData(String rocketName, StageDataDTO stageDTO) {
        this.rocketName = rocketName;
        this.stageLevel = stageDTO.getStageLevel();
        this.fuel = stageDTO.getFuel();
        this.isActivated = stageDTO.isActivated();
        if (stageDTO.getPosition() != null) {
            this.position = new Position(stageDTO.getPosition());
        } else {
            this.position = null;
        }
        this.speed = stageDTO.getSpeed();
        this.acceleration = stageDTO.getAcceleration();
        this.timestamp = stageDTO.getTimestamp();
        this.angle = stageDTO.getAngle();
        this.legsDeployed = stageDTO.isLegsDeployed();
    }

    @Override
    public String toString() {
        return "StageData{" +
                "rocketName=" + rocketName +
                ", stageLevel=" + stageLevel +
                ", fuel=" + fuel +
                ", isActivated=" + isActivated +
                ", position=" + position +
                ", speed=" + speed +
                ", acceleration=" + acceleration +
                ", angle=" + angle +
                ", legsDeployed=" + legsDeployed +
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
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

    public Double getAltitude() {
        return this.position.getAltitude();
    }

    public void setAltitude(Double altitude) {
        this.position.setAltitude(altitude);
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }
}
