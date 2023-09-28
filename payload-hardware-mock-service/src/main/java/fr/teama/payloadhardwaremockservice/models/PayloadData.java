package fr.teama.payloadhardwaremockservice.models;

import java.time.LocalDateTime;

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

    @Override
    public String toString() {
        return "PayloadData: " +
                "position=" + position ;
    }

}
