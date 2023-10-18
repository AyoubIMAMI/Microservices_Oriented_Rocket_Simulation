package fr.teama.payloadservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class PayloadData {

    @GeneratedValue
    @Id
    private Long id;
    private String rocketName;
    private Position position;
    private LocalDateTime timestamp;

    public PayloadData(String rocketName, Position position, LocalDateTime timestamp) {
        this.rocketName = rocketName;
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

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        rocketName = rocketName;
    }

    @Override
    public String toString() {
        return "PayloadData{" +
                "position=" + position +
                '}';
    }
}
