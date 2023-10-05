package fr.teama.telemetryservice.controllers.dto;

import java.time.LocalDateTime;
import java.util.List;

public class RocketDataDTO {

    private PositionDTO position;

    private Double speed;

    private Double acceleration;

    private List<StageDataDTO> stages;

    private LocalDateTime timestamp;

    private Double status;

    public RocketDataDTO() {

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

    public List<StageDataDTO> getStages() {
        return stages;
    }

    public void setStages(List<StageDataDTO> stages) {
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
