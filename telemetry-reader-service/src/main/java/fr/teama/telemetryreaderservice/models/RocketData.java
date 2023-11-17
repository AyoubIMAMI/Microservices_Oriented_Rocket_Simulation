package fr.teama.telemetryreaderservice.models;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RocketData {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;

    String name;

    @ElementCollection
    private List<StageData> stages;

    private Position position;

    private Double speed;

    private Double acceleration;

    private LocalDateTime timestamp;

    private Double status;

    public RocketData(String name) {
    }

    public RocketData() {

    }

    public StageData getStageByLevel(int stageLevel){
        for (StageData stage: stages){
            if (stage.getStageLevel()==stageLevel)
                return stage;
        }
        return null;
    }

    @Override
    public String toString() {
        return "RocketData{" +
                "name=" + name +
                ", stages=" + stages +
                ", position=" + position +
                ", speed=" + speed +
                ", acceleration=" + acceleration +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }

    public List<StageData> getStages() {
        return stages;
    }

    public void setStages(List<StageData> stages) {
        this.stages = stages;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }

    public Double getAltitude() {
        return this.position.getAltitude();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
