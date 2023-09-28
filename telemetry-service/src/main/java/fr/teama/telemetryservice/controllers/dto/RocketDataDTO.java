package fr.teama.telemetryservice.controllers.dto;

import java.time.LocalDateTime;
import java.util.List;

public class RocketDataDTO {

    private Double altitude;

    private Double speed;

    private Double acceleration;

    private List<StageDataDTO> stages;

    private LocalDateTime timestamp;

    private Double status;

    public RocketDataDTO(List<StageDataDTO> stages) {
        this.altitude = 0.0;
        this.speed = 0.0;
        this.stages = stages;
        this.status = 1.0;
        this.acceleration = 0.0;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setStages(List<StageDataDTO> stages) {
        this.stages = stages;
    }

    public RocketDataDTO() {
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public List<StageDataDTO> getStages() {
        return stages;
    }

    @Override
    public String toString() {
        return "RocketData{" +
                "altitude=" + altitude +
                ", stages=" + stages +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }
}
