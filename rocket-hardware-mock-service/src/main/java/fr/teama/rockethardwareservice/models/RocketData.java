package fr.teama.rockethardwareservice.models;

import java.time.LocalDateTime;
import java.util.List;

public class RocketData {

    private Position position;

    private Double speed;

    private Double acceleration;

    private List<StageData> stages;

    private LocalDateTime timestamp;

    private Double status;

    public RocketData(List<StageData> stages) {
        this.position = new Position(0.0, 0.0, 0.0);
        this.speed = 0.0;
        this.stages = stages;
        this.status = RocketStates.NORMAL.getValue();
        this.acceleration = 0.0;
    }

    @Override
    public String toString() {
        return "RocketData{" +
                "position=" + position +
                ", speed=" + speed +
                ", stages=" + stages +
                ", status=" + status +
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

    public List<StageData> getStages() {
        return stages;
    }

    public void setStages(List<StageData> stages) {
        this.stages = stages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }
}
