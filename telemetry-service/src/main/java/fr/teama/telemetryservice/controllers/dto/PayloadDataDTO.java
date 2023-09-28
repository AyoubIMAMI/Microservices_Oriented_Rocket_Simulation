package fr.teama.telemetryservice.controllers.dto;

import java.time.LocalDateTime;

public class PayloadDataDTO {
    private PositionDTO position;

    private LocalDateTime timestamp;

    public PayloadDataDTO() {
        this.position=new PositionDTO();
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
}
