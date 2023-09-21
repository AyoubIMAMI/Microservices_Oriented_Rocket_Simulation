package fr.teama.telemetryservice.controllers.dto;

import java.time.LocalDateTime;
import java.util.List;

public class RocketDataDTO {

    private Double altitude;

    private Double speed;

    private List<StageDataDTO> stages;

    private LocalDateTime timestamp;

    public RocketDataDTO(List<StageDataDTO> stages) {
        this.altitude = 0.0;
        this.speed = 0.0;
        this.stages = stages;
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
        return "RocketDataDTO{" +
                "altitude=" + altitude +
                ", speed=" + speed +
                ", stages=" + stages +
                ", timestamp=" + timestamp +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
