package fr.teama.telemetryservice.models;

import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RocketData {

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

    public RocketData(String name, RocketDataDTO rocketDTO) {
        this.name = name;
        this.position = new Position(rocketDTO.getPosition());
        this.speed=rocketDTO.getSpeed();
        this.stages=new ArrayList<>();
        this.status = rocketDTO.getStatus();
        this.acceleration=rocketDTO.getAcceleration();
        this.timestamp=rocketDTO.getTimestamp();
        rocketDTO.getStages().forEach(stageDTO -> this.stages.add(new StageData(name, stageDTO)));
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
}
