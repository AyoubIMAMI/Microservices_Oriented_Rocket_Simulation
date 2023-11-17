package fr.teama.scientificdepartmentservice.models;


import java.time.LocalDateTime;
import java.util.Random;


public class RobotData {

    private Position position;

    private LocalDateTime timestamp;

    private Double speed;

    private Double acceleration;
    private Sample sample;

    private Double angle;

    private boolean isParachuteDeployed;

    public RobotData(Position position) {
        this.position = position;
        this.position.setAltitude(1000.0); // Robot is drop when rocket is at 1000dm altitude of the ground of the moon
        this.speed = 0.0;
        this.acceleration = 0.0;
        this.isParachuteDeployed = false;
        this.angle = (Math.random() * 35 + 10) * (new Random().nextBoolean() ? 1 : -1);
    }

    public RobotData() {

    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "RobotData{" +
                "position=" + position +
                ", speed=" + speed +
                ", isParachuteDeployed=" + isParachuteDeployed +
                ", angle=" + angle +
                '}';
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

    public boolean isParachuteDeployed() {
        return isParachuteDeployed;
    }

    public void setParachuteDeployed(boolean parachuteDeployed) {
        isParachuteDeployed = parachuteDeployed;
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
