package fr.teama.telemetryservice.models;

import fr.teama.telemetryservice.controllers.dto.RobotDataDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Embeddable
@Entity
public class RobotData {

    @Id
    @GeneratedValue
    private Long id;

    String rocketName;

    private Position position;

    private Double speed;

    private Double acceleration;

    private Double angle;

    private LocalDateTime timestamp;

    private boolean isParachuteDeployed;
    @Embedded
    private Sample sample;

    public RobotData() {

    }

    public RobotData(String rocketName, RobotDataDTO robotDTO) {
        this.rocketName = rocketName;
        if (robotDTO.getPosition() != null) {
            this.position = new Position(robotDTO.getPosition());
        } else {
            this.position = null;
        }
        this.speed = robotDTO.getSpeed();
        this.acceleration = robotDTO.getAcceleration();
        this.timestamp = robotDTO.getTimestamp();
        this.angle = robotDTO.getAngle();
        this.isParachuteDeployed = robotDTO.isParachuteDeployed();
        this.sample = robotDTO.getSample();
    }

    @Override
    public String toString() {
        return "RobotData{" +
                "rocketName=" + rocketName +
                ", position=" + position +
                ", speed=" + speed +
                ", acceleration=" + acceleration +
                ", angle=" + angle +
                ", isParachuteDeployed=" + isParachuteDeployed +
                ", timestamp=" + timestamp +
                '}';
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

    public boolean isParachuteDeployed() {
        return isParachuteDeployed;
    }

    public void setParachuteDeployed(boolean parachuteDeployed) {
        isParachuteDeployed = parachuteDeployed;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }
}
