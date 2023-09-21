package fr.teama.telemetryservice.controllers.dto;

import java.util.List;

public class RocketDTO {

    private Double altitude;

    private Double speed;

    private List<StageDTO> stages;

    public RocketDTO(List<StageDTO> stages) {
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

    public List<StageDTO> getStages() {
        return stages;
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "altitude=" + altitude +
                ", speed=" + speed +
                ", stages=" + stages +
                '}';
    }
}
