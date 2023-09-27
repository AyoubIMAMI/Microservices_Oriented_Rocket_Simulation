package fr.teama.payloadhadwaremockservice.models;

import java.time.LocalDateTime;
import java.util.List;

public class PayloadData {
    private Position position;

    private LocalDateTime timestamp;

    public PayloadData() {
        this.position=new Position();
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
}
