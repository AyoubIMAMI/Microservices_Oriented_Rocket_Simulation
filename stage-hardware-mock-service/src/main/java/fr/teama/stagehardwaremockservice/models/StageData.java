package fr.teama.stagehardwaremockservice.models;

import fr.teama.stagehardwaremockservice.controllers.dto.StageDataDTO;
import fr.teama.stagehardwaremockservice.controllers.dto.StageFullStateDTO;

import java.time.LocalDateTime;
import java.util.Random;

public class StageData {

    private int stageLevel;

    private Double fuel;

    private boolean isActivated;

    private Position position;

    private Double speed;

    private Double acceleration;

    private LocalDateTime timestamp;

    private Double angle;

    private boolean legsDeployed;

    public StageData(StageFullStateDTO fullStage) {
        StageDataDTO stageData = fullStage.getStageData();
        this.position = new Position(fullStage.getPosition());
        this.stageLevel = stageData.getStageLevel();
        this.fuel = stageData.getFuel();
        this.isActivated = stageData.isActivated();
        this.speed = fullStage.getSpeed() * 0.6; // Loss of speed due to staging
        this.angle = (Math.random() * 35 + 10) * (new Random().nextBoolean() ? 1 : -1); // Random angle between 10 and 45 degrees
        this.legsDeployed = false;
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

    @Override
    public String toString() {
        return "StageData{" +
                "position=" + position +
                ", stageLevel=" + stageLevel +
                ", speed=" + speed +
                '}';
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

    public Double getX() {
        return this.position.getX();
    }

    public void setX(Double x) {
        this.position.setX(x);
    }

    public Double getY() {
        return this.position.getY();
    }

    public void setY(Double y) {
        this.position.setY(y);
    }
}
