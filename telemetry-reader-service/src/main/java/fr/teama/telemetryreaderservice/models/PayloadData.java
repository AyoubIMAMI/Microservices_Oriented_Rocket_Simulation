package fr.teama.telemetryreaderservice.models;


import java.time.LocalDateTime;

public class PayloadData {

    private String rocketName;

    private Position position;

    private LocalDateTime timestamp;

    public PayloadData() {
        this.rocketName = rocketName;
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

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    @Override
    public String toString() {
        return "PayloadData{" +
                "rocketName='" + rocketName + '\'' +
                ", position=" + position +
                '}';
    }
}
