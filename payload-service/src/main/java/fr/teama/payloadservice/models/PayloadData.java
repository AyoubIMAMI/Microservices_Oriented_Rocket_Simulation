package fr.teama.payloadservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class PayloadData {
    private Position position;

    private LocalDateTime timestamp;
    @GeneratedValue
    @Id
    private Long id;

    public PayloadData(Position position, LocalDateTime timestamp) {
        this.position=position;
        this.timestamp=timestamp;
    }

    public PayloadData() {

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PayloadData{" +
                "position=" + position +
                '}';
    }
}
