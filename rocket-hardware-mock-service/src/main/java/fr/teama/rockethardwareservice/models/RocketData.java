package fr.teama.rockethardwareservice.models;

import java.time.LocalDateTime;
import java.util.List;

public class RocketData {

    private Double altitude;

    private Double speed;

    private List<StageData> stages;

    private LocalDateTime timestamp;

    private boolean isInNormalState;

    public RocketData(List<StageData> stages) {
        this.altitude = 0.0;
        this.speed = 0.0;
        this.stages = stages;
        isInNormalState = true;
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

    public List<StageData> getStages() {
        return stages;
    }

    @Override
    public String toString() {
        return "RocketData{" +
                "altitude=" + altitude +
                ", stages=" + stages +
                ", isInNormalState=" + isInNormalState +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isInNormalState() {
        return isInNormalState;
    }

    public void setInNormalState(boolean inNormalState) {
        isInNormalState = inNormalState;
    }
}
