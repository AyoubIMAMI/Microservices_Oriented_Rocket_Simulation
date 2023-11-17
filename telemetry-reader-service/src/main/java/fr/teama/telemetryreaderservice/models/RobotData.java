package fr.teama.telemetryreaderservice.models;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Embeddable
@Entity
public class RobotData {

    @jakarta.persistence.Id
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
