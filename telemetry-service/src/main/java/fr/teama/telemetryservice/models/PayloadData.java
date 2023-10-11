package fr.teama.telemetryservice.models;

import fr.teama.telemetryservice.controllers.dto.PayloadDataDTO;
import fr.teama.telemetryservice.controllers.dto.PositionDTO;

import java.time.LocalDateTime;

public class PayloadData {

    private String rocketName;

    private PositionDTO position;

    private LocalDateTime timestamp;

    public PayloadData(String rocketName, PayloadDataDTO payloadDTO) {
        this.rocketName = rocketName;
        this.position = payloadDTO.getPosition();
        this.timestamp = payloadDTO.getTimestamp();
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
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
